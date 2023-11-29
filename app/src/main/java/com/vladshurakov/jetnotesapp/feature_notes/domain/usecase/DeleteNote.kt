package com.vladshurakov.jetnotesapp.feature_notes.domain.usecase

import com.vladshurakov.jetnotesapp.feature_notes.domain.models.Note
import com.vladshurakov.jetnotesapp.feature_notes.domain.repository.NotesRepository

class DeleteNote(private val repository: NotesRepository) {
    suspend operator fun invoke(note: Note) = repository.delete(note)
}