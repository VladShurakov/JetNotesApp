package com.example.simplenotesapp.ui.screens.deleted_notes

import com.example.simplenotesapp.domain.models.Note

data class DeletedNotesState(
    val deletedNotes: List<Note> = emptyList(),
)