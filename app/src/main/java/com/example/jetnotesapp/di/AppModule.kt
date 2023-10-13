package com.example.jetnotesapp.di

import android.content.Context
import androidx.room.Room
import com.example.jetnotesapp.data.database.NoteDatabase
import com.example.jetnotesapp.data.repository.NoteRepositoryImpl
import com.example.jetnotesapp.data.repository.SettingsRepositoryImlp
import com.example.jetnotesapp.domain.repository.NoteRepository
import com.example.jetnotesapp.domain.repository.SettingsRepository
import com.example.jetnotesapp.domain.usecase.DeleteNote
import com.example.jetnotesapp.domain.usecase.GetNoteById
import com.example.jetnotesapp.domain.usecase.GetNotes
import com.example.jetnotesapp.domain.usecase.GetSettings
import com.example.jetnotesapp.domain.usecase.InsertNote
import com.example.jetnotesapp.domain.usecase.SaveSettings
import com.example.jetnotesapp.domain.usecase.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext context: Context): NoteDatabase{
        return Room.databaseBuilder(
            context = context,
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
    fun provideSettingsRepository(@ApplicationContext context: Context): SettingsRepository {
        return SettingsRepositoryImlp(context = context)
    }

    @Provides
    @Singleton
    fun provideUseCases(noteRepository: NoteRepository, settingsRepository: SettingsRepository): UseCases{
        return UseCases(
            insertNote = InsertNote(repository = noteRepository),
            getNoteById = GetNoteById(repository = noteRepository),
            deleteNote = DeleteNote(repository = noteRepository),
            getNotes = GetNotes(repository = noteRepository),
            getSettings = GetSettings(repository = settingsRepository),
            saveSettings = SaveSettings(repository = settingsRepository),
            )
    }

}