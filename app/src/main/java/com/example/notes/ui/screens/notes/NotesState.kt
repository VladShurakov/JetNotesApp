package com.example.notes.ui.screens.notes

import com.example.notes.domain.models.Note
import com.example.notes.domain.util.NotesOrder
import com.example.notes.domain.util.OrderType

data class NotesState(
    var notes: List<Note> = emptyList(),
    var orderType: OrderType = OrderType.Descending,
    var notesOrder: NotesOrder = NotesOrder.Timestamp
)