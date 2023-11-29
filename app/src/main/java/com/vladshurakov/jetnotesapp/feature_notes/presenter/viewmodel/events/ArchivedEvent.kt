package com.vladshurakov.jetnotesapp.feature_notes.presenter.viewmodel.events

import com.vladshurakov.jetnotesapp.feature_notes.domain.models.Note

sealed interface ArchivedEvent {
    data class Unarchive(val note: Note): ArchivedEvent
    data class Delete(val note: Note): ArchivedEvent
}