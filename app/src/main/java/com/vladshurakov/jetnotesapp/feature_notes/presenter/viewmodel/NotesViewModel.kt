package com.vladshurakov.jetnotesapp.feature_notes.presenter.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladshurakov.jetnotesapp.feature_notes.domain.models.Folder
import com.vladshurakov.jetnotesapp.feature_notes.domain.models.Note
import com.vladshurakov.jetnotesapp.feature_notes.domain.usecase.NotesUseCases
import com.vladshurakov.jetnotesapp.feature_notes.presenter.viewmodel.events.NotesEvent
import com.vladshurakov.jetnotesapp.feature_settings.domain.usecase.SettingsUseCases
import com.vladshurakov.jetnotesapp.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
 * State for the Notes screen
 */
data class NotesState(
    var notes: List<Note> = emptyList(),
    var orderType: OrderType = OrderType.Descending,
    var query: String = ""
)

/*
 * ViewModel for the Notes screen and Settings screen
 */
@HiltViewModel
class NotesViewModel @Inject constructor(
    private val notesUseCases: NotesUseCases,
    private val settingsUseCases: SettingsUseCases
) : ViewModel() {

    private val _notesState = mutableStateOf(NotesState())
    val notesState: State<NotesState> = _notesState

    init {
        getNotes()
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            NotesEvent.ToggleOrderType -> {
                _notesState.value.orderType = when (_notesState.value.orderType) {
                    OrderType.Descending -> OrderType.Ascending
                    OrderType.Ascending -> OrderType.Descending
                }
                settingsUseCases.saveSettings.invoke(
                    settingsUseCases.getSettings.invoke()
                        .copy(orderType = _notesState.value.orderType)
                )
                getNotes()
            }

            is NotesEvent.Delete -> {
                viewModelScope.launch {
                    if (event.note.id == null)
                        return@launch
                    notesUseCases.moveTo(event.note.id, Folder.DELETED)
                }
            }

            is NotesEvent.GetNotes -> {
               getNotes()
            }

            is NotesEvent.Search -> {
                _notesState.value = notesState.value.copy(
                    query = event.value
                )
                notesUseCases.searchNotes.invoke(
                    notesState.value.query,
                    notesState.value.orderType
                ).onEach { notes ->
                    _notesState.value = notesState.value.copy(
                        notes = notes
                    )
                }.launchIn(viewModelScope)
            }

            is NotesEvent.Archive -> {
                viewModelScope.launch {
                    if (event.note.id == null)
                        return@launch
                    notesUseCases.moveTo(event.note.id, Folder.ARCHIVED)
                }
            }

            is NotesEvent.TogglePin -> {
                viewModelScope.launch {
                    if (event.note.id == null)
                        return@launch
                    notesUseCases.updatePinned(event.note.id, event.note.pinned)
                }
            }
        }
    }


    private fun getNotes() {
        val orderType = settingsUseCases.getSettings.invoke().orderType
        notesUseCases.getNotes(orderType, Folder.NOTES).onEach { notes ->
            _notesState.value = notesState.value.copy(
                notes = notes,
                orderType = orderType,
                query = String()
            )
        }.launchIn(viewModelScope)
    }
}