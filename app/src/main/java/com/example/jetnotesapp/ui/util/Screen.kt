package com.example.jetnotesapp.ui.util

sealed class Screen(val route: String) {

    object Notes: Screen("note_screen")

    object AddEditNote: Screen("add_edit_note_screen")

    object Settings: Screen("settings_screen")

    object DeletedNotes: Screen("deleted_notes_screen")
}