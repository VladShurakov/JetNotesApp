package com.vladshurakov.jetnotesapp.domain.usecase

import com.vladshurakov.jetnotesapp.domain.models.Note
import com.vladshurakov.jetnotesapp.domain.repository.NoteRepository

class GetNoteById(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(id: Long): Note? {
        return repository.getNoteById(id = id)
    }
}