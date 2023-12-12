package com.vladshurakov.jetnotesapp.feature_notes.data.repository

import com.vladshurakov.jetnotesapp.feature_notes.data.data_source.NoteDao
import com.vladshurakov.jetnotesapp.feature_notes.domain.models.Folder
import com.vladshurakov.jetnotesapp.feature_notes.domain.models.NoteEntity
import com.vladshurakov.jetnotesapp.feature_notes.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow

class NotesRepositoryImpl(private val noteDao: NoteDao) : NotesRepository {
    override suspend fun insert(note: NoteEntity): Long {
        return noteDao.insert(note)
    }

    override suspend fun insert(notes: List<NoteEntity>): List<Long> {
        return noteDao.insert(notes)
    }

    override fun getAll(): List<NoteEntity> {
        return noteDao.getAll()
    }

    override suspend fun get(id: Long): NoteEntity? {
        return noteDao.get(id)
    }

    override suspend fun delete(note: NoteEntity) {
        noteDao.delete(note)
    }

    override suspend fun moveTo(id: Long, folder: Folder) {
        noteDao.moveTo(id, folder)
    }

    override suspend fun updatePinned(id: Long, pinned: Boolean) {
        noteDao.updatePinned(id, pinned)
    }

    override fun getDesc(folder: Folder): Flow<List<NoteEntity>> {
        return noteDao.getDesc(folder)
    }

    override fun getAsc(folder: Folder): Flow<List<NoteEntity>> {
        return noteDao.getAsc(folder)
    }

    override fun getDesc(query: String): Flow<List<NoteEntity>> {
        return noteDao.getDesc(query)
    }

    override fun getAsc(query: String): Flow<List<NoteEntity>> {
        return noteDao.getAsc(query)
    }
}