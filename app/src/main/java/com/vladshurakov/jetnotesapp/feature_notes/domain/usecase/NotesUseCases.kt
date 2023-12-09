package com.vladshurakov.jetnotesapp.feature_notes.domain.usecase

data class NotesUseCases(
    val insertNote: InsertNote,
    val insertNotes: InsertNotes,
    val getAllNotes: GetAllNotes,
    val getNote: GetNote,
    val deleteNote: DeleteNote,
    val moveTo: MoveNoteToFolder,
    val updatePinned: UpdatePinned,
    val getNotes:GetNotes,
    val searchNotes: SearchNotes,
)