package com.vladshurakov.jetnotesapp.feature_notes.domain.usecase

import com.vladshurakov.jetnotesapp.feature_notes.domain.models.NoteEntity
import com.vladshurakov.jetnotesapp.feature_notes.domain.repository.NotesRepository

class InsertNote(private val repository: NotesRepository){

    suspend operator fun invoke(note: NoteEntity): Long?{
        if (note.title.isBlank() && (note.content.isBlank() || note.content.startsWith(' '))){
            return -1
        }
        if (note.title.isBlank() && note.content.isNotBlank()){
            return repository.insert(note.copy(title = "Title"))
        }
        return repository.insert(note)
    }
}