package com.vladshurakov.jetnotesapp.feature_notes.presenter.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladshurakov.jetnotesapp.feature_notes.domain.models.Folder
import com.vladshurakov.jetnotesapp.feature_notes.domain.models.NoteEntity
import com.vladshurakov.jetnotesapp.feature_notes.domain.usecase.NotesUseCases
import com.vladshurakov.jetnotesapp.feature_notes.presenter.viewmodel.events.EditEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
 * State for the Add/Edit screen
 */
data class NoteTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)

/*
 * ViewModel for the Add/Edit screen
 */
@HiltViewModel
class EditViewModel @Inject constructor(
    private val notesUseCases: NotesUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var pinned by mutableStateOf(false)
    private var folder by mutableStateOf(Folder.NOTES)

    private val _noteTitle = mutableStateOf(NoteTextFieldState())
    private val _noteContent = mutableStateOf(NoteTextFieldState())

    val noteTitle: State<NoteTextFieldState> = _noteTitle
    val noteContent: State<NoteTextFieldState> = _noteContent

    private var noteTitleCopy: String = _noteTitle.value.text
    private var noteContentCopy = _noteContent.value.text

    var timestamp: Long = 0
    private var currentNoteId: Long? = null

    init {
        savedStateHandle.get<Long>("id")?.let { noteId ->
            if (noteId != (-1).toLong()) {
                viewModelScope.launch {
                    notesUseCases.getNote(noteId)?.also { note ->
                        pinned = note.pinned
                        folder = note.folder

                        _noteTitle.value = _noteTitle.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )
                        _noteContent.value = _noteContent.value.copy(
                            text = note.content,
                            isHintVisible = false
                        )

                        noteTitleCopy = _noteTitle.value.text
                        noteContentCopy = _noteContent.value.text

                        timestamp = note.timestamp
                        currentNoteId = note.id
                    }
                }
            } else {
                timestamp = System.currentTimeMillis()
            }
        }
    }

    fun onEvent(event: EditEvent) {
        when (event) {
            is EditEvent.EnteredTitle -> {
                _noteTitle.value = _noteTitle.value.copy(
                    text = event.value
                )
            }

            is EditEvent.ChangeTitleFocus -> {
                _noteTitle.value = _noteTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused && _noteTitle.value.text.isBlank()
                )
            }

            is EditEvent.EnteredContent -> {
                _noteContent.value = _noteContent.value.copy(
                    text = event.value
                )
            }

            is EditEvent.ChangeContentFocus -> {
                _noteContent.value = _noteContent.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            _noteContent.value.text.isBlank()
                )
            }

            is EditEvent.Insert -> {
                if (noteContentCopy == noteContent.value.text && noteTitleCopy == noteTitle.value.text){
                    return
                }
                viewModelScope.launch {
                    currentNoteId = notesUseCases.insertNote(
                        NoteEntity(
                            pinned = pinned,
                            folder = folder,
                            title = noteTitle.value.text,
                            timestamp = System.currentTimeMillis(),
                            content = noteContent.value.text,
                            id = currentNoteId
                        )
                    )
                }
            }
        }
    }
}