package com.vladshurakov.jetnotesapp.domain.usecase

import com.vladshurakov.jetnotesapp.domain.models.Note
import com.vladshurakov.jetnotesapp.domain.repository.NoteRepository

class DeleteNote(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note = note)
    }
}