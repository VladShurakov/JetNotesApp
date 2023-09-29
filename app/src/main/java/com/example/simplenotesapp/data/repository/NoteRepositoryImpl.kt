package com.example.simplenotesapp.data.repository

import com.example.simplenotesapp.data.database.NoteDao
import com.example.simplenotesapp.data.database.models.NoteEntity
import com.example.simplenotesapp.domain.models.Note
import com.example.simplenotesapp.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {

    override suspend fun insertNote(note: Note): Long {
        return noteDao.insertNote(mapToNoteEntity(note = note))
    }

    override suspend fun getNoteById(id: Long): Note? {
        return noteDao.getNoteById(id)?.let { note ->
            mapToNote(noteEntity = note)
        }
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(mapToNoteEntity(note = note))
    }

    override fun getNotes(): Flow<List<Note>> {
        return noteDao.getNotes().map { listOfNoteEntity ->
            listOfNoteEntity.map { noteEntity ->
                mapToNote(noteEntity = noteEntity)
            }
        }
    }

    private fun mapToNoteEntity(note: Note): NoteEntity {
        return NoteEntity(
            title = note.title, content = note.content,
            timestamp = note.timestamp, deleted = note.deleted,
            id = note.id
        )
    }

    private fun mapToNote(noteEntity: NoteEntity): Note {
        return Note(
            title = noteEntity.title, content = noteEntity.content,
            timestamp = noteEntity.timestamp, deleted = noteEntity.deleted,
            id = noteEntity.id
        )
    }

}