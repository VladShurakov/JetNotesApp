package com.vladshurakov.jetnotesapp.ui.screens.deleted_notes

import com.vladshurakov.jetnotesapp.domain.models.Note

sealed interface DeletedNotesEvent {

    data class RecoverNote(val note: Note): DeletedNotesEvent

    data class DeleteNote(val note: Note): DeletedNotesEvent
}