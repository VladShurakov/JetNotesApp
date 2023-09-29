package com.example.simplenotesapp.ui.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplenotesapp.domain.models.SettingsBundle
import com.example.simplenotesapp.domain.usecase.UseCases
import com.example.simplenotesapp.domain.util.NotesOrder
import com.example.simplenotesapp.domain.util.OrderType
import com.example.simplenotesapp.ui.screens.notes.MainEvent
import com.example.simplenotesapp.ui.screens.notes.NotesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _state = mutableStateOf(
        NotesState()
    )
    val state: State<NotesState> = _state

    init {
        getNotes(
            notesOrder = useCases.getSettings.invoke().notesOrder,
            orderType =  useCases.getSettings.invoke().orderType
        )
    }

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.ChangeNotesOrder -> {
                if (useCases.getSettings.invoke().notesOrder == event.notesOrder) {
                    return
                }
                _state.value.notesOrder = event.notesOrder
                saveSettings(settingsBundle = useCases.getSettings.invoke().copy(notesOrder = event.notesOrder))
                getNotes(event.notesOrder, _state.value.orderType)
            }

            is MainEvent.ChangeNotesOrderType -> {
                if (_state.value.orderType == OrderType.Descending) {
                    _state.value.orderType = OrderType.Ascending
                } else {
                    _state.value.orderType = OrderType.Descending
                }
                saveSettings(settingsBundle = useCases.getSettings.invoke().copy(orderType = _state.value.orderType))
                getNotes(_state.value.notesOrder, _state.value.orderType)
            }

            is MainEvent.ToTrash -> {
                viewModelScope.launch {
                    useCases.insertNote(event.note.copy(deleted = true))
                }
            }

        }
    }

    fun getSettings(): SettingsBundle {
        return useCases.getSettings.invoke()
    }

    fun saveSettings(settingsBundle: SettingsBundle){
        useCases.saveSettings.invoke(settingsBundle = settingsBundle)
    }

    private fun getNotes(notesOrder: NotesOrder, orderType: OrderType) {
        useCases.getAllNotes(
            notesOrder = notesOrder,
            orderType = orderType,
            inTrash = false
        ).onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    notesOrder = notesOrder,
                    orderType = orderType
                )
            }.launchIn(viewModelScope)
    }
}