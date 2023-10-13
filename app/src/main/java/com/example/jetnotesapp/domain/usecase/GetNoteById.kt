package com.example.jetnotesapp.domain.usecase

import com.example.jetnotesapp.domain.models.Note
import com.example.jetnotesapp.domain.repository.NoteRepository

class GetNoteById(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(id: Long): Note? {
        return repository.getNoteById(id = id)
    }
}