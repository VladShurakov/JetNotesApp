package com.example.notes.ui.screens.add_edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.domain.models.Note
import com.example.notes.domain.usecase.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val notesUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _noteTitle = mutableStateOf(
        NoteTextFieldState()
    )
    val noteTitle: State<NoteTextFieldState> = _noteTitle

    val timestamp = mutableLongStateOf(0)

    private val _noteContent = mutableStateOf(
        NoteTextFieldState()
    )
    val noteContent: State<NoteTextFieldState> = _noteContent

    private var currentNoteId: Long? = null

    init {
        savedStateHandle.get<Long>("id")?.let { noteId ->
            if (noteId != (-1).toLong()) {
                viewModelScope.launch {
                    notesUseCases.getNoteById(noteId)?.also { note ->

                        currentNoteId = note.id

                        _noteTitle.value = noteTitle.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )

                        _noteContent.value = _noteContent.value.copy(
                            text = note.content,
                            isHintVisible = false
                        )

                        timestamp.longValue = note.timestamp

                    }
                }
            } else {
                timestamp.longValue = System.currentTimeMillis()
            }
        }
    }

    fun onEvent(event: AddEditNoteEvent) {
        when (event) {
            is AddEditNoteEvent.EnteredTitle -> {
                _noteTitle.value = noteTitle.value.copy(
                    text = event.value
                )
            }

            is AddEditNoteEvent.ChangeTitleFocus -> {
                _noteTitle.value = noteTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteTitle.value.text.isBlank()
                )
            }

            is AddEditNoteEvent.EnteredContent -> {
                _noteContent.value = _noteContent.value.copy(
                    text = event.value
                )
            }

            is AddEditNoteEvent.ChangeContentFocus -> {
                _noteContent.value = _noteContent.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            _noteContent.value.text.isBlank()
                )
            }

            is AddEditNoteEvent.InsertNote -> {
                viewModelScope.launch {
                    currentNoteId = notesUseCases.insertNote(
                        Note(
                            title = noteTitle.value.text,
                            content = noteContent.value.text,
                            timestamp = System.currentTimeMillis(),
                            inTrash = false,
                            id = currentNoteId
                        )
                    )
                }
            }
        }
    }
}