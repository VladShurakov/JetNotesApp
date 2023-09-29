package com.example.simplenotesapp.ui.screens.notes

import com.example.simplenotesapp.domain.models.Note
import com.example.simplenotesapp.domain.util.NotesOrder

sealed interface MainEvent {

    data class ChangeNotesOrder(val notesOrder: NotesOrder): MainEvent

    object ChangeNotesOrderType: MainEvent

    data class ToTrash(val note: Note): MainEvent

}