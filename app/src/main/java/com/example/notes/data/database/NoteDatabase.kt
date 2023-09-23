package com.example.notes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notes.data.database.models.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDatabase() : RoomDatabase() {

    abstract val noteDao: NoteDao
}