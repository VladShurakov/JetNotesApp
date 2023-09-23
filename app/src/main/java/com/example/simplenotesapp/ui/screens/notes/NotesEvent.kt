package com.example.simplenotesapp.ui.screens.notes

import com.example.simplenotesapp.domain.models.Note
import com.example.simplenotesapp.domain.util.NotesOrder

sealed interface NotesEvent {
    data class ChangeNotesOrder(val notesOrder: NotesOrder): NotesEvent
    object ChangeOrderType: NotesEvent
    data class ToTrash(val note: Note): NotesEvent
}