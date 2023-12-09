package com.vladshurakov.jetnotesapp.feature_notes.presenter.viewmodel.events

import com.vladshurakov.jetnotesapp.feature_notes.domain.models.Note

/*
 * Event for the Notes screen
 */
sealed interface NotesEvent {
    data object ToggleOrderType: NotesEvent
    data class Archive(val note: Note): NotesEvent
    data class Delete(val note: Note): NotesEvent
    data object GetNotes : NotesEvent
    data class Search(val value: String): NotesEvent
    data class TogglePin(val note: Note): NotesEvent
}