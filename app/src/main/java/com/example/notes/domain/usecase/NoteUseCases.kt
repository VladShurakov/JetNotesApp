package com.example.notes.domain.usecase

data class NoteUseCases(

    val insertNote: InsertNote,

    val getNoteById: GetNoteById,

    val deleteNote: DeleteNote,

    val getAllNotes: GetAllNotes,

    val getNotesInTrash: GetNotesInTrash,
)
