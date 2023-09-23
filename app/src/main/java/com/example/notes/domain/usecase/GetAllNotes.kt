package com.example.notes.domain.usecase

import com.example.notes.domain.models.Note
import com.example.notes.domain.repository.NoteRepository
import com.example.notes.domain.util.NotesOrder
import com.example.notes.domain.util.OrderType
import com.example.notes.domain.util.sortedNotes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllNotes(
    private val repository: NoteRepository
) {

    operator fun invoke(
        notesOrder: NotesOrder = NotesOrder.Timestamp,
        order: OrderType
    ): Flow<List<Note>> {
        val sortedNotes = sortedNotes(
            allNotes = repository.getNotes(),
            notesOrder = notesOrder,
            orderType = order
        ).map {
            it.filter { note ->
                note.inTrash == false
            }
        }
        return sortedNotes
    }
}