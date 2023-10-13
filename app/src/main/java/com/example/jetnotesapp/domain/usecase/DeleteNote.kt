package com.example.jetnotesapp.domain.usecase

import com.example.jetnotesapp.domain.models.Note
import com.example.jetnotesapp.domain.repository.NoteRepository

class DeleteNote(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note = note)
    }
}