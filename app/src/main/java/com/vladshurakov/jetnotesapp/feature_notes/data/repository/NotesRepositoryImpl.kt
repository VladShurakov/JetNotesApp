package com.vladshurakov.jetnotesapp.feature_notes.data.repository

import com.vladshurakov.jetnotesapp.feature_notes.data.data_source.NoteDao
import com.vladshurakov.jetnotesapp.feature_notes.domain.models.Folder
import com.vladshurakov.jetnotesapp.feature_notes.domain.models.Note
import com.vladshurakov.jetnotesapp.feature_notes.domain.models.toNote
import com.vladshurakov.jetnotesapp.feature_notes.domain.models.toNoteEntity
import com.vladshurakov.jetnotesapp.feature_notes.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NotesRepositoryImpl(private val noteDao: NoteDao) : NotesRepository {
    override suspend fun insert(note: Note): Long {

        return noteDao.insert(note.toNoteEntity())
    }

    override suspend fun get(id: Long): Note? {
        return noteDao.get(id)?.toNote()
    }

    override suspend fun delete(note: Note) {
        noteDao.delete(note.toNoteEntity())
    }

    override suspend fun moveTo(id: Long, folder: Folder) {
        noteDao.moveTo(id, folder)
    }

    override suspend fun updatePinned(id: Long, pinned: Boolean) {
        noteDao.updatePinned(id, pinned)
    }

    override fun getDesc(): Flow<List<Note>> {
        return noteDao.getDesc().map { listOfNoteEntity ->
            listOfNoteEntity.map { noteEntity ->
                noteEntity.toNote()
            }
        }
    }

    override fun getAsc(): Flow<List<Note>> {
        return noteDao.getAsc().map { listOfNoteEntity ->
            listOfNoteEntity.map { noteEntity ->
                noteEntity.toNote()
            }
        }
    }

    override fun getDesc(query: String): Flow<List<Note>> {
        return noteDao.getDesc(query).map { listOfNoteEntity ->
            listOfNoteEntity.map { noteEntity ->
                noteEntity.toNote()
            }
        }
    }

    override fun getAsc(query: String): Flow<List<Note>> {
        return noteDao.getAsc(query).map { listOfNoteEntity ->
            listOfNoteEntity.map { noteEntity ->
                noteEntity.toNote()
            }
        }
    }
}