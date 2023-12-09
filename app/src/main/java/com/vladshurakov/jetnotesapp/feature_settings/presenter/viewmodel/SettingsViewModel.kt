package com.vladshurakov.jetnotesapp.feature_settings.presenter.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladshurakov.jetnotesapp.feature_notes.domain.models.Note
import com.vladshurakov.jetnotesapp.feature_notes.domain.usecase.NotesUseCases
import com.vladshurakov.jetnotesapp.feature_settings.domain.models.SettingsBundle
import com.vladshurakov.jetnotesapp.feature_settings.domain.usecase.SettingsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
 * ViewModel for Settings
 */
@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsUseCases: SettingsUseCases,
    private val notesUseCases: NotesUseCases
) : ViewModel() {

    private val _settingsBundle = mutableStateOf(SettingsBundle())
    val settingsBundle = _settingsBundle

    init {
        settingsBundle.value = settingsUseCases.getSettings.invoke()
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.SaveSettings -> {
                settingsUseCases.saveSettings.invoke(settingsBundle = event.settingsBundle)
                _settingsBundle.value = event.settingsBundle
            }
        }
    }

    fun getAll(): List<Note> {
        return notesUseCases.getAllNotes.invoke()
    }

    fun insert(notes: List<Note>) {
        viewModelScope.launch {
            notesUseCases.insertNotes(notes)
        }
    }
}