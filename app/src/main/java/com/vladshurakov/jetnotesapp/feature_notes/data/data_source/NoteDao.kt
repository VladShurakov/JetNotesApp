package com.vladshurakov.jetnotesapp.feature_notes.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vladshurakov.jetnotesapp.feature_notes.domain.models.Folder
import com.vladshurakov.jetnotesapp.feature_notes.domain.models.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(noteEntity: NoteEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(noteEntities: List<NoteEntity>): List<Long>

    @Query("SELECT * FROM note")
    fun getAll(): List<NoteEntity>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun get(id: Long): NoteEntity?

    @Delete
    suspend fun delete(noteEntity: NoteEntity)

    @Query("UPDATE Note SET folder = :folder WHERE id = :id")
    suspend fun moveTo(id: Long, folder: Folder)

    @Query("UPDATE Note SET pinned = :pinned WHERE id = :id")
    suspend fun updatePinned(id: Long, pinned: Boolean)

    @Query("SELECT * FROM note WHERE folder = :folder ORDER BY pinned DESC, timestamp DESC")
    fun getDesc(folder: Folder): Flow<List<NoteEntity>>

    @Query("SELECT * FROM note WHERE folder = :folder ORDER BY pinned DESC, timestamp ASC")
    fun getAsc(folder: Folder): Flow<List<NoteEntity>>

    @Query(
        "SELECT * FROM note WHERE " +
        "(title LIKE '%' || :query || '%' OR content LIKE '%' || :query || '%') " +
        "AND folder = 'NOTES' ORDER BY pinned DESC, timestamp DESC"
    )
    fun getDesc(query: String): Flow<List<NoteEntity>>

    @Query(
        "SELECT * FROM note WHERE " +
        "(title LIKE '%' || :query || '%' OR content LIKE '%' || :query || '%') " +
        "AND folder = 'NOTES' ORDER BY pinned ASC, timestamp ASC"
    )
    fun getAsc(query: String): Flow<List<NoteEntity>>
}