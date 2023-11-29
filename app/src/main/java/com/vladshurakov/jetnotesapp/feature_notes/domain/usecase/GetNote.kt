package com.vladshurakov.jetnotesapp.feature_notes.domain.usecase

import com.vladshurakov.jetnotesapp.feature_notes.domain.models.Note
import com.vladshurakov.jetnotesapp.feature_notes.domain.repository.NotesRepository

class GetNote(private val repository: NotesRepository) {
    suspend operator fun invoke(id: Long): Note? = repository.get(id)
}