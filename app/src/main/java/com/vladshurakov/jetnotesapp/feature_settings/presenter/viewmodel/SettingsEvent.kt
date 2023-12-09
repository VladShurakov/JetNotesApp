package com.vladshurakov.jetnotesapp.feature_settings.presenter.viewmodel

import com.vladshurakov.jetnotesapp.feature_notes.domain.models.Note
import com.vladshurakov.jetnotesapp.feature_settings.domain.models.SettingsBundle

/*
 * Event for the Settings screen
 */
sealed interface SettingsEvent {
    data class SaveSettings(val settingsBundle: SettingsBundle): SettingsEvent
    data class InsertNotes(val notes: List<Note>): SettingsEvent
}