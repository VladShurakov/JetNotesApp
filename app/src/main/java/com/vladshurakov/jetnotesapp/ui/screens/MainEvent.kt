package com.vladshurakov.jetnotesapp.ui.screens

import com.vladshurakov.jetnotesapp.domain.models.Note
import com.vladshurakov.jetnotesapp.domain.util.NotesOrder

/*
 * Event for the Notes screen and Settings screen
 */
sealed interface MainEvent {

    data class ChangeNotesOrder(val notesOrder: NotesOrder): MainEvent

    data object ChangeOrderType: MainEvent

    data class Delete(val note: Note): MainEvent

    data class EnteredSearch(val value: String): MainEvent
}