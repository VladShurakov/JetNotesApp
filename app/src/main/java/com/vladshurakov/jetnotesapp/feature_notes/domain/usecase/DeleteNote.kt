package com.vladshurakov.jetnotesapp.feature_notes.domain.usecase

import com.vladshurakov.jetnotesapp.feature_notes.domain.models.NoteEntity
import com.vladshurakov.jetnotesapp.feature_notes.domain.repository.NotesRepository

class DeleteNote(private val repository: NotesRepository) {
    suspend operator fun invoke(note: NoteEntity) = repository.delete(note)
}