package com.example.notes.ui.screens.deleted_notes

import com.example.notes.domain.models.Note

sealed interface DeletedNotesEvent {
    data class RecoverNote(val note: Note): DeletedNotesEvent
    data class DeleteNote(val note: Note): DeletedNotesEvent
}