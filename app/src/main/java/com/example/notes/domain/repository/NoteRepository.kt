package com.example.notes.domain.repository

import com.example.notes.domain.models.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun insertNote(note: Note): Long?

    suspend fun getNoteById(id: Long): Note?

    suspend fun deleteNote(note: Note)

    fun getNotes(): Flow<List<Note>>
}