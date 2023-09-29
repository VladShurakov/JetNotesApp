package com.example.simplenotesapp.di

import android.app.Application
import androidx.room.Room
import com.example.simplenotesapp.data.database.NoteDatabase
import com.example.simplenotesapp.data.repository.NoteRepositoryImpl
import com.example.simplenotesapp.data.repository.SettingsRepositoryImlp
import com.example.simplenotesapp.domain.repository.NoteRepository
import com.example.simplenotesapp.domain.repository.SettingsRepository
import com.example.simplenotesapp.domain.usecase.DeleteNote
import com.example.simplenotesapp.domain.usecase.GetAllNotes
import com.example.simplenotesapp.domain.usecase.GetNoteById
import com.example.simplenotesapp.domain.usecase.GetSettings
import com.example.simplenotesapp.domain.usecase.InsertNote
import com.example.simplenotesapp.domain.usecase.SaveSettings
import com.example.simplenotesapp.domain.usecase.UseCases
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
            context = app.applicationContext,
            klass = NoteDatabase::class.java,
            name = "notes-database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(database: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(noteDao = database.noteDao)
    }

    @Provides
    @Singleton
    fun provideSettingsRepository(app: Application): SettingsRepository {
        return SettingsRepositoryImlp(context = app.applicationContext)
    }

    @Provides
    @Singleton
    fun provideUseCases(noteRepository: NoteRepository, settingsRepository: SettingsRepository): UseCases{
        return UseCases(
            insertNote = InsertNote(repository = noteRepository),
            getNoteById = GetNoteById(repository = noteRepository),
            deleteNote = DeleteNote(repository = noteRepository),
            getAllNotes = GetAllNotes(repository = noteRepository),
            getSettings = GetSettings(repository = settingsRepository),
            saveSettings = SaveSettings(repository = settingsRepository),
            )
    }

}