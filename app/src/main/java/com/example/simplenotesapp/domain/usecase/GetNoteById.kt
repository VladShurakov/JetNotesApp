package com.example.simplenotesapp.domain.usecase

import com.example.simplenotesapp.domain.models.Note
import com.example.simplenotesapp.domain.repository.NoteRepository

class GetNoteById(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(id: Long): Note? {
        return repository.getNoteById(id = id)
    }
}