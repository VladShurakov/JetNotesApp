package com.vladshurakov.jetnotesapp.di

import android.content.Context
import androidx.room.Room
import com.vladshurakov.jetnotesapp.feature_notes.data.data_source.NoteDatabase
import com.vladshurakov.jetnotesapp.feature_notes.data.repository.NotesRepositoryImpl
import com.vladshurakov.jetnotesapp.feature_notes.domain.repository.NotesRepository
import com.vladshurakov.jetnotesapp.feature_notes.domain.usecase.DeleteNote
import com.vladshurakov.jetnotesapp.feature_notes.domain.usecase.GetNote
import com.vladshurakov.jetnotesapp.feature_notes.domain.usecase.GetNotes
import com.vladshurakov.jetnotesapp.feature_notes.domain.usecase.InsertNote
import com.vladshurakov.jetnotesapp.feature_notes.domain.usecase.MoveNoteToFolder
import com.vladshurakov.jetnotesapp.feature_notes.domain.usecase.NotesUseCases
import com.vladshurakov.jetnotesapp.feature_notes.domain.usecase.SearchNotes
import com.vladshurakov.jetnotesapp.feature_notes.domain.usecase.UpdatePinned
import com.vladshurakov.jetnotesapp.feature_settings.data.repository.SettingsRepositoryImpl
import com.vladshurakov.jetnotesapp.feature_settings.domain.repository.SettingsRepository
import com.vladshurakov.jetnotesapp.feature_settings.domain.usecase.GetSettings
import com.vladshurakov.jetnotesapp.feature_settings.domain.usecase.SaveSettings
import com.vladshurakov.jetnotesapp.feature_settings.domain.usecase.SettingsUseCases
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
    fun provideNoteDatabase(@ApplicationContext context: Context): NoteDatabase {
    fun provideNoteDatabase(@ApplicationContext context: Context): NoteDatabase{
        return Room.databaseBuilder(
            context = context,
            klass = NoteDatabase::class.java,
            name = "notes-database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(database: NoteDatabase): NotesRepository {
        return NotesRepositoryImpl(noteDao = database.noteDao)
    }

    @Provides
    @Singleton
    fun provideNotesUseCases(notesRepository: NotesRepository): NotesUseCases {
        return NotesUseCases(
            insertNote = InsertNote(notesRepository),
            getNote = GetNote(notesRepository),
            deleteNote = DeleteNote(notesRepository),
            moveTo = MoveNoteToFolder(notesRepository),
            updatePinned = UpdatePinned(notesRepository),
            getNotes = GetNotes(notesRepository),
            searchNotes = SearchNotes(notesRepository)
            )
    }

    @Provides
    @Singleton
    fun provideSettingsRepository(@ApplicationContext context: Context): SettingsRepository {
        return SettingsRepositoryImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideSettingsUseCases(settingsRepository: SettingsRepository): SettingsUseCases {
        return SettingsUseCases(
            getSettings = GetSettings(repository = settingsRepository),
            saveSettings = SaveSettings(repository = settingsRepository),
        )
    }
}