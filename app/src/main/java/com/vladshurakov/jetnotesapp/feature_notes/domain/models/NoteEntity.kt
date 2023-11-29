package com.vladshurakov.jetnotesapp.feature_notes.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Note")
data class NoteEntity(
    @ColumnInfo(name = "pinned")     val pinned: Boolean = false,
    @ColumnInfo(name = "folder")     val folder: Folder = Folder.NOTES,
    @ColumnInfo(name = "title")      val title: String,
    @ColumnInfo(name = "timestamp")  val timestamp: Long,
    @ColumnInfo(name = "content")    val content: String,
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
)

// Convert NoteEntity to Note
fun NoteEntity.toNote(): Note{
    return Note(
        pinned = pinned, folder = folder, title = title,
        timestamp = timestamp, content = content, id = id
    )
}