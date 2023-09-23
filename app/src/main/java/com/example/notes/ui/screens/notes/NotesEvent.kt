package com.example.notes.ui.screens.notes

import com.example.notes.domain.models.Note
import com.example.notes.domain.util.NotesOrder

sealed interface NotesEvent {
    data class ChangeNotesOrder(val notesOrder: NotesOrder): NotesEvent
    object ChangeOrderType: NotesEvent
    data class ToTrash(val note: Note): NotesEvent
}