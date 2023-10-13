package com.example.jetnotesapp.ui.screens.deleted_notes

import com.example.jetnotesapp.domain.models.Note

sealed interface DeletedNotesEvent {

    data class RecoverNote(val note: Note): DeletedNotesEvent

    data class DeleteNote(val note: Note): DeletedNotesEvent
}