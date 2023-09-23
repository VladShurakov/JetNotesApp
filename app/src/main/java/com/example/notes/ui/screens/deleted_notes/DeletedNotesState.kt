package com.example.notes.ui.screens.deleted_notes

import com.example.notes.domain.models.Note

data class DeletedNotesState(
    val deletedNotes: List<Note> = emptyList(),
)