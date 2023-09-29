package com.example.simplenotesapp.ui.screens.notes

import com.example.simplenotesapp.domain.models.Note
import com.example.simplenotesapp.domain.util.NotesOrder
import com.example.simplenotesapp.domain.util.OrderType

data class NotesState(

    var notes: List<Note> = emptyList(),

    var orderType: OrderType = OrderType.Descending,

    var notesOrder: NotesOrder = NotesOrder.Timestamp
)