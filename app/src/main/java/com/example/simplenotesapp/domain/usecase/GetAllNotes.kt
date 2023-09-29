package com.example.simplenotesapp.domain.usecase

import com.example.simplenotesapp.domain.models.Note
import com.example.simplenotesapp.domain.repository.NoteRepository
import com.example.simplenotesapp.domain.util.NotesOrder
import com.example.simplenotesapp.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllNotes(
    private val repository: NoteRepository
) {

    operator fun invoke(
        notesOrder: NotesOrder = NotesOrder.Timestamp,
        orderType: OrderType,
        inTrash: Boolean
    ): Flow<List<Note>> {
        val sortedNotes = sortedNotes(
            allNotes = repository.getNotes(),
            notesOrder = notesOrder,
            orderType = orderType
        ).map {
            it.filter { note ->
                note.deleted == inTrash
            }
        }
        return sortedNotes
    }

    private fun sortedNotes(
        allNotes: Flow<List<Note>>,
        notesOrder: NotesOrder,
        orderType: OrderType
    ): Flow<List<Note>> {
        return allNotes.map { notes ->
            when (orderType) {
                OrderType.Ascending -> {
                    when (notesOrder) {
                        NotesOrder.Timestamp -> {
                            notes.sortedBy { note ->
                                note.timestamp
                            }
                        }

                        NotesOrder.Title -> {
                            notes.sortedBy { note ->
                                note.title
                            }
                        }
                    }
                }

                OrderType.Descending -> {
                    when (notesOrder) {
                        NotesOrder.Timestamp -> {
                            notes.sortedByDescending { note ->
                                note.timestamp
                            }
                        }

                        NotesOrder.Title -> {
                            notes.sortedByDescending { note ->
                                note.title
                            }
                        }
                    }
                }
            }
        }
    }

}