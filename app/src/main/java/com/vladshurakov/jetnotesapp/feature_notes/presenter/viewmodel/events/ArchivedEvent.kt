package com.vladshurakov.jetnotesapp.feature_notes.presenter.viewmodel.events

import com.vladshurakov.jetnotesapp.feature_notes.domain.models.NoteEntity

sealed interface ArchivedEvent {
    data class Unarchive(val note: NoteEntity): ArchivedEvent
    data class Delete(val note: NoteEntity): ArchivedEvent
}