package com.example.jetnotesapp.ui.screens

import com.example.jetnotesapp.domain.models.Note
import com.example.jetnotesapp.domain.util.NotesOrder

/*
 * Event for the Notes screen and Settings screen
 */
sealed interface MainEvent {

    data class ChangeNotesOrder(val notesOrder: NotesOrder): MainEvent

    object ChangeOrderType: MainEvent

    data class Delete(val note: Note): MainEvent

    data class EnteredSearch(val value: String): MainEvent
}