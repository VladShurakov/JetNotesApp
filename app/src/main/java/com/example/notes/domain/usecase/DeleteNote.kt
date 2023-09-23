package com.example.notes.domain.usecase

import com.example.notes.domain.models.Note
import com.example.notes.domain.repository.NoteRepository

class DeleteNote(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note = note)
    }
}