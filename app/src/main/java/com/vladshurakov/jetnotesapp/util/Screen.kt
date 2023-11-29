package com.vladshurakov.jetnotesapp.util

sealed class Screen(val route: String) {
    data object Notes: Screen("notes")
    data object AddEditNote: Screen("edit_note")
    data object Settings: Screen("settings_")
    data object DeletedNotes: Screen("deleted_notes")
    data object ArchivedNotes: Screen("archived_notes")
}