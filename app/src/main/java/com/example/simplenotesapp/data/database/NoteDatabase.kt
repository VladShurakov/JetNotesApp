package com.example.simplenotesapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.simplenotesapp.data.database.models.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract val noteDao: NoteDao
}