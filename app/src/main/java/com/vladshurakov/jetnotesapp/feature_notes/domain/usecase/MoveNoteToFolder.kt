package com.vladshurakov.jetnotesapp.feature_notes.domain.usecase

import com.vladshurakov.jetnotesapp.feature_notes.domain.models.Folder
import com.vladshurakov.jetnotesapp.feature_notes.domain.repository.NotesRepository

class MoveNoteToFolder(private val repository: NotesRepository) {
    suspend operator fun invoke(id: Long, folder: Folder) = repository.moveTo(id, folder)
}