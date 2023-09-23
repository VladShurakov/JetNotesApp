package com.example.simplenotesapp.domain.usecase

import com.example.simplenotesapp.domain.models.Note
import com.example.simplenotesapp.domain.repository.NoteRepository

class DeleteNote(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note = note)
    }
}