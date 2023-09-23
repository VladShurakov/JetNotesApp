package com.example.notes.domain.util

import com.example.notes.domain.models.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun sortedNotes(
    allNotes: Flow<List<Note>>,
    notesOrder: NotesOrder,
    orderType: OrderType
): Flow<List<Note>> {

    return allNotes.map { notes ->
        when (orderType) {
            OrderType.Ascending -> {
                when (notesOrder) {
                    is NotesOrder.Timestamp -> {
                        notes.sortedBy { note ->
                            note.timestamp
                        }
                    }

                    is NotesOrder.Title -> {
                        notes.sortedBy { note ->
                            note.title
                        }
                    }
                }
            }

            OrderType.Descending -> {
                when (notesOrder) {
                    is NotesOrder.Timestamp -> {
                        notes.sortedByDescending { note ->
                            note.timestamp
                        }
                    }

                    is NotesOrder.Title -> {
                        notes.sortedByDescending { note ->
                            note.title
                        }
                    }
                }
            }
        }
    }

}