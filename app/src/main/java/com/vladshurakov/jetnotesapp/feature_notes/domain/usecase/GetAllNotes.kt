package com.vladshurakov.jetnotesapp.feature_notes.domain.usecase

import com.vladshurakov.jetnotesapp.feature_notes.domain.models.NoteEntity
import com.vladshurakov.jetnotesapp.feature_notes.domain.repository.NotesRepository

class GetAllNotes(private val repository: NotesRepository) {
    operator fun invoke(): List<NoteEntity> {
        return repository.getAll()
    }
}