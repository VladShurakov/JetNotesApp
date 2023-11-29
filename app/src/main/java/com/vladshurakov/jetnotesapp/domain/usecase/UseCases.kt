package com.vladshurakov.jetnotesapp.domain.usecase

data class UseCases(
    val insertNote: InsertNote,
    val getNoteById: GetNoteById,
    val deleteNote: DeleteNote,
    val getNotes: GetNotes,
    val getSettings: GetSettings,
    val saveSettings: SaveSettings,
)
