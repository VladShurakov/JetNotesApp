package com.example.notes.domain.usecase

import com.example.notes.domain.models.Note
import com.example.notes.domain.repository.NoteRepository

class GetNoteById(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(id: Long): Note? {
        return repository.getNoteById(id = id)
    }
}