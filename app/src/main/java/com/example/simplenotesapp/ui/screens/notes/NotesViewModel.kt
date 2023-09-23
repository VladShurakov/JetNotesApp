package com.example.simplenotesapp.ui.screens.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplenotesapp.domain.usecase.NoteUseCases
import com.example.simplenotesapp.domain.util.NotesOrder
import com.example.simplenotesapp.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    init {
        getNotes(_state.value.notesOrder, _state.value.orderType)
    }

    fun onEvent(event: NotesEvent) {
        when (event) {

            is NotesEvent.ChangeNotesOrder -> {
                if (_state.value.notesOrder::class.java == event.notesOrder::class.java){
                    return
                }
                getNotes(event.notesOrder, _state.value.orderType)
            }

            is NotesEvent.ChangeOrderType -> {
                if (_state.value.orderType == OrderType.Descending){
                    _state.value = _state.value.copy(orderType = OrderType.Ascending)
                }else {
                    _state.value = _state.value.copy(orderType = OrderType.Descending)
                }
                getNotes(_state.value.notesOrder, _state.value.orderType)
            }

            is NotesEvent.ToTrash -> {
                viewModelScope.launch {
                    noteUseCases.insertNote(event.note.copy(inTrash = true))
                }
            }

        }
    }

    private fun getNotes(notesOrder: NotesOrder, orderType: OrderType) {
        noteUseCases.getAllNotes(notesOrder, orderType)
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    notesOrder = notesOrder,
                    orderType = orderType
                )
            }
            .launchIn(viewModelScope)
    }
}