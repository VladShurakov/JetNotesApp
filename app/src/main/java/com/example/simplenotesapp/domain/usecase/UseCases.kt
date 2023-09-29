package com.example.simplenotesapp.domain.usecase

data class UseCases(

    val insertNote: InsertNote,

    val getNoteById: GetNoteById,

    val deleteNote: DeleteNote,

    val getAllNotes: GetAllNotes,

    val getSettings: GetSettings,

    val saveSettings: SaveSettings,
)
