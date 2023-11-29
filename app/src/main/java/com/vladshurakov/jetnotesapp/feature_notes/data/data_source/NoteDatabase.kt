package com.vladshurakov.jetnotesapp.feature_notes.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vladshurakov.jetnotesapp.feature_notes.domain.models.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 2,
    exportSchema = false
)
abstract class NoteDatabase : RoomDatabase() {

    abstract val noteDao: NoteDao
}