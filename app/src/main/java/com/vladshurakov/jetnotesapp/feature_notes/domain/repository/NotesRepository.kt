package com.vladshurakov.jetnotesapp.feature_notes.domain.repository

import com.vladshurakov.jetnotesapp.feature_notes.domain.models.Folder
import com.vladshurakov.jetnotesapp.feature_notes.domain.models.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    suspend fun insert(note: NoteEntity): Long?
    suspend fun insert(notes: List<NoteEntity>): List<Long?>
    fun getAll(): List<NoteEntity>
    suspend fun get(id: Long): NoteEntity?
    suspend fun delete(note: NoteEntity)
    suspend fun moveTo(id: Long, folder: Folder)
    suspend fun updatePinned(id: Long, pinned: Boolean)
    fun getDesc(folder: Folder): Flow<List<NoteEntity>>
    fun getAsc(folder: Folder): Flow<List<NoteEntity>>
    fun getDesc(query: String): Flow<List<NoteEntity>>
    fun getAsc(query: String): Flow<List<NoteEntity>>
}