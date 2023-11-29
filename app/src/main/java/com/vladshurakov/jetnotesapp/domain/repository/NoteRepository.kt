package com.vladshurakov.jetnotesapp.domain.repository

import com.vladshurakov.jetnotesapp.domain.models.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun insertNote(note: Note): Long?

    suspend fun getNoteById(id: Long): Note?

    suspend fun deleteNote(note: Note)

    fun getNotesByTitle(query: String, deleted: Boolean, isDesk: Boolean): Flow<List<Note>>

    fun getNotesByTimestamp(query: String, deleted: Boolean, isDesk: Boolean): Flow<List<Note>>
}