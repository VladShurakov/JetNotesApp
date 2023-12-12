package com.vladshurakov.jetnotesapp.feature_notes.domain.usecase

import com.vladshurakov.jetnotesapp.feature_notes.domain.models.NoteEntity
import com.vladshurakov.jetnotesapp.feature_notes.domain.repository.NotesRepository

class InsertNotes(private val repository: NotesRepository) {
    suspend operator fun invoke(notes: List<NoteEntity>): List<Long?> {
        return repository.insert(notes)
    }
}