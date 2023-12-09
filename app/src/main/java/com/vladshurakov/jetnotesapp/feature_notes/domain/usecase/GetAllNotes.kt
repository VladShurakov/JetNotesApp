package com.vladshurakov.jetnotesapp.feature_notes.domain.usecase

import com.vladshurakov.jetnotesapp.feature_notes.domain.models.Note
import com.vladshurakov.jetnotesapp.feature_notes.domain.repository.NotesRepository

class GetAllNotes(private val repository: NotesRepository) {
    operator fun invoke(): List<Note> {
        return repository.getAll()
    }
}