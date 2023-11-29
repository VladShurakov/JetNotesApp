package com.vladshurakov.jetnotesapp.feature_notes.domain.usecase

import com.vladshurakov.jetnotesapp.feature_notes.domain.repository.NotesRepository

class UpdatePinned(private val repository: NotesRepository) {
    suspend operator fun invoke(id: Long, pinned: Boolean) = repository.updatePinned(id, pinned)
}