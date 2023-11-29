package com.vladshurakov.jetnotesapp.feature_notes.domain.models

data class Note(
    val pinned: Boolean = false,
    val folder: Folder = Folder.NOTES,
    var title: String,
    val timestamp: Long,
    val content: String,
    val id: Long? = null,
)

// Convert Note to NoteEntity
fun Note.toNoteEntity(): NoteEntity {
    return NoteEntity(
        pinned = pinned, folder = folder, title = title,
        timestamp = timestamp, content = content, id = id
    )
}