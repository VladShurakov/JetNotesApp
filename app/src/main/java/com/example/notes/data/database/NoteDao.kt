package com.example.notes.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notes.data.database.models.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(noteEntity: NoteEntity): Long

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteById(id: Long): NoteEntity?

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<NoteEntity>>
}