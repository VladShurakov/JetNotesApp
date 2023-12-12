package com.vladshurakov.jetnotesapp.feature_notes.presenter.viewmodel.events

import com.vladshurakov.jetnotesapp.feature_notes.domain.models.NoteEntity

sealed interface DeletedEvent {
    data class Restore(val note: NoteEntity): DeletedEvent
    data class Delete(val note: NoteEntity): DeletedEvent
}