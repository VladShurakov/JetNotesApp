package com.vladshurakov.jetnotesapp.feature_notes.presenter.viewmodel.events

import com.vladshurakov.jetnotesapp.feature_notes.domain.models.NoteEntity

/*
 * Event for the Notes screen
 */
sealed interface NotesEvent {
    data object ToggleOrderType: NotesEvent
    data class Archive(val note: NoteEntity): NotesEvent
    data class Delete(val note: NoteEntity): NotesEvent
    data object GetNotes : NotesEvent
    data class Search(val value: String): NotesEvent
    data class TogglePin(val note: NoteEntity): NotesEvent
}