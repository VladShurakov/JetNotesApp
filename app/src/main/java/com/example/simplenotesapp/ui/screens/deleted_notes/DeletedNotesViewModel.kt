package com.example.simplenotesapp.ui.screens.deleted_notes

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
class DeletedNotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val _state = mutableStateOf(DeletedNotesState())
    val state: State<DeletedNotesState> = _state

    init {
        getDeletedNotes()
    }

    fun onEvent(event: DeletedNotesEvent){
        when (event){
            is DeletedNotesEvent.RecoverNote -> {
                viewModelScope.launch {
                    noteUseCases.insertNote(event.note.copy(inTrash = false))
                }
            }
            is DeletedNotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(event.note)
                }
            }
        }
    }

    private fun getDeletedNotes() {
        noteUseCases.getNotesInTrash(
            notesOrder = NotesOrder.Timestamp,
            orderType = OrderType.Descending
        )
            .onEach { notes ->
                _state.value = state.value.copy(
                    deletedNotes = notes
                )
            }
            .launchIn(viewModelScope)
    }
}