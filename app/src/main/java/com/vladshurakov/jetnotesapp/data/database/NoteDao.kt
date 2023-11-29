package com.vladshurakov.jetnotesapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vladshurakov.jetnotesapp.data.database.models.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(noteEntity: NoteEntity): Long

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteById(id: Long): NoteEntity?

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity)

    @Query(
        "SELECT * FROM note WHERE deleted = :deleted AND " +
        "(title LIKE '%' || :query || '%' OR content LIKE '%' || :query || '%') " +
        "ORDER BY CASE WHEN :isDesk = 1 THEN title END DESC, CASE WHEN :isDesk = 0 THEN title END ASC"
    )
    fun getNotesByTitle(
        query: String,
        deleted: Boolean,
        isDesk: Boolean
    ): Flow<List<NoteEntity>>

    @Query(
        "SELECT * FROM note WHERE deleted = :deleted AND " +
        "(title LIKE '%' || :query || '%' OR content LIKE '%' || :query || '%') " +
        "ORDER BY CASE WHEN :isDesk = 1 THEN timestamp END DESC, CASE WHEN :isDesk = 0 THEN timestamp END ASC"
    )
    fun getNotesByTimestamp(
        query: String,
        deleted: Boolean,
        isDesk: Boolean
    ): Flow<List<NoteEntity>>
}