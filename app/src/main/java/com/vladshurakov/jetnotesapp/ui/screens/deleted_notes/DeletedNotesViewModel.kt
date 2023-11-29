package com.vladshurakov.jetnotesapp.ui.screens.deleted_notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladshurakov.jetnotesapp.domain.models.Note
import com.vladshurakov.jetnotesapp.domain.usecase.UseCases
import com.vladshurakov.jetnotesapp.domain.util.NotesOrder
import com.vladshurakov.jetnotesapp.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
 * ViewModel for the Deleted notes screen
 */
@HiltViewModel
class DeletedNotesViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _state = mutableStateOf(emptyList<Note>())
    val state: State<List<Note>> = _state

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
        useCases.getNotes(
            query = "",
            deleted = true,
            notesOrder = NotesOrder.Timestamp,
            orderType = OrderType.Descending
        ).onEach { notes ->
            _state.value = notes
        }.launchIn(viewModelScope)
    }
}