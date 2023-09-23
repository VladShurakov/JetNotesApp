package com.example.simplenotesapp.di

import android.app.Application
import androidx.room.Room
import com.example.simplenotesapp.data.database.NoteDatabase
import com.example.simplenotesapp.data.repository.NoteRepositoryImpl
import com.example.simplenotesapp.domain.repository.NoteRepository
import com.example.simplenotesapp.domain.usecase.DeleteNote
import com.example.simplenotesapp.domain.usecase.GetNoteById
import com.example.simplenotesapp.domain.usecase.GetAllNotes
import com.example.simplenotesapp.domain.usecase.GetNotesInTrash
import com.example.simplenotesapp.domain.usecase.NoteUseCases
import com.example.simplenotesapp.domain.usecase.InsertNote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase{
        return Room.databaseBuilder(
            app.applicationContext,
            NoteDatabase::class.java,
            "notes-database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(database: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(database.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases{
        return NoteUseCases(
            insertNote = InsertNote(repository = repository),
            getNoteById = GetNoteById(repository = repository),
            deleteNote = DeleteNote(repository = repository),
            getAllNotes = GetAllNotes(repository = repository),
            getNotesInTrash = GetNotesInTrash(repository = repository),
            )
    }
//
//    @Provides
//    @Singleton
//    fun provideDataStore(): DataSto{
//
//    }

}