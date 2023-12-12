package com.vladshurakov.jetnotesapp.feature_notes.presenter.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladshurakov.jetnotesapp.feature_notes.domain.models.Folder
import com.vladshurakov.jetnotesapp.feature_notes.domain.models.NoteEntity
import com.vladshurakov.jetnotesapp.feature_notes.domain.usecase.NotesUseCases
import com.vladshurakov.jetnotesapp.feature_notes.presenter.viewmodel.events.ArchivedEvent
import com.vladshurakov.jetnotesapp.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
 * ViewModel for the Deleted notes screen
 */
@HiltViewModel
class ArchivedViewModel @Inject constructor(
    private val notesUseCases: NotesUseCases
) : ViewModel() {

    private val _state = mutableStateOf(emptyList<NoteEntity>())
    val state: State<List<NoteEntity>> = _state

    init {
        getArchivedNotes()
    }

    fun onEvent(event: ArchivedEvent) {
        when (event) {
            is ArchivedEvent.Unarchive -> {
                if (event.note.id == null)
                    return
                viewModelScope.launch {
                    notesUseCases.moveTo(event.note.id, Folder.NOTES)
                }
            }

            is ArchivedEvent.Delete -> {
                if (event.note.id == null)
                    return
                viewModelScope.launch {
                    notesUseCases.moveTo(event.note.id, Folder.DELETED)
                }
            }
        }
    }

    private fun getArchivedNotes() {
        notesUseCases.getNotes(OrderType.Descending, Folder.ARCHIVED).onEach { notes ->
            _state.value = notes
        }.launchIn(viewModelScope)
    }
}