package com.example.simplenotesapp.domain.usecase

import com.example.simplenotesapp.domain.models.Note
import com.example.simplenotesapp.domain.repository.NoteRepository

class InsertNote(
    private val repository: NoteRepository
){

    suspend operator fun invoke(note: Note): Long?{
        if (note.title.isBlank() && note.content.isBlank())
            return null
        if (note.title.isBlank() && (note.content.isNotBlank() || !note.content.startsWith(' ')))
            note.title = note.content.takeWhile { (it != ' ') && (it != '\n') }.take(12)
        return repository.insertNote(note = note)
    }
}