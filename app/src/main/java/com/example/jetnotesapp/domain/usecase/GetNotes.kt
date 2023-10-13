package com.example.jetnotesapp.domain.usecase

import com.example.jetnotesapp.domain.models.Note
import com.example.jetnotesapp.domain.repository.NoteRepository
import com.example.jetnotesapp.domain.util.NotesOrder
import com.example.jetnotesapp.domain.util.OrderType
import kotlinx.coroutines.flow.Flow

class GetNotes(
    private val repository: NoteRepository
) {

    operator fun invoke(
        query : String,
        deleted: Boolean,
        notesOrder: NotesOrder,
        orderType: OrderType
    ): Flow<List<Note>> {
        return when (orderType) {
            OrderType.Ascending -> {
                when (notesOrder) {
                    NotesOrder.Timestamp -> {
                        repository.getNotesByTimestamp(query = query, deleted = deleted, isDesk = false)
                    }

                    NotesOrder.Title -> {
                        repository.getNotesByTitle(query = query, deleted = deleted, isDesk = false)
                    }
                }
            }

            OrderType.Descending -> {
                when (notesOrder) {
                    NotesOrder.Timestamp -> {
                        repository.getNotesByTimestamp(query = query, deleted = deleted, isDesk = true)
                    }

                    NotesOrder.Title -> {
                        repository.getNotesByTitle(query = query, deleted = deleted, isDesk = true)
                    }
                }
            }
        }
    }
}