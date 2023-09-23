package com.example.simplenotesapp.domain.usecase

import com.example.simplenotesapp.domain.models.Note
import com.example.simplenotesapp.domain.repository.NoteRepository
import com.example.simplenotesapp.domain.util.NotesOrder
import com.example.simplenotesapp.domain.util.OrderType
import com.example.simplenotesapp.domain.util.sortedNotes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesInTrash(
    private val repository: NoteRepository
) {

    operator fun invoke(
        notesOrder: NotesOrder = NotesOrder.Timestamp,
        orderType: OrderType
    ): Flow<List<Note>> {
        val sortedNotes = sortedNotes(
            allNotes = repository.getNotes(),
            notesOrder = notesOrder,
            orderType = orderType
        ).map {
            it.filter { note ->
                note.inTrash == true
            }
        }
        return sortedNotes
    }
}