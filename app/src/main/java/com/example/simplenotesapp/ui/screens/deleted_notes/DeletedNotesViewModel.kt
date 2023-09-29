package com.example.simplenotesapp.ui.screens.deleted_notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplenotesapp.domain.usecase.UseCases
import com.example.simplenotesapp.domain.util.NotesOrder
import com.example.simplenotesapp.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeletedNotesViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _state = mutableStateOf(DeletedNotesState())
    val state: State<DeletedNotesState> = _state

    init {
        getDeletedNotes()
    }

    fun onEvent(event: DeletedNotesEvent) {
        when (event) {
            is DeletedNotesEvent.RecoverNote -> {
                viewModelScope.launch {
                    useCases.insertNote(event.note.copy(deleted = false))
                }
            }

            is DeletedNotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    useCases.deleteNote(event.note)
                }
            }
        }
    }

    private fun getDeletedNotes() {
        useCases.getAllNotes(
            notesOrder = NotesOrder.Timestamp,
            orderType = OrderType.Descending,
            inTrash = true
        ).onEach { notes ->
                _state.value = state.value.copy(
                    deletedNotes = notes
                )
            }.launchIn(viewModelScope)
    }
}