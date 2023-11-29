package com.vladshurakov.jetnotesapp.feature_notes.presenter.viewmodel.events

import com.vladshurakov.jetnotesapp.feature_notes.domain.models.Note

sealed interface DeletedEvent {
    data class Restore(val note: Note): DeletedEvent
    data class Delete(val note: Note): DeletedEvent
}