package com.vladshurakov.jetnotesapp.feature_settings.presenter.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladshurakov.jetnotesapp.feature_notes.domain.models.NoteEntity
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
                saveSettings(event.settingsBundle)
            }

            is SettingsEvent.InsertNotes -> {
                insertNotes(event.notes)
            }
        }
    }

    fun getAllNotes(): List<NoteEntity> {
        return notesUseCases.getAllNotes.invoke()
    }

    private fun saveSettings(settingsBundle: SettingsBundle){
        settingsUseCases.saveSettings.invoke(settingsBundle = settingsBundle)
        _settingsBundle.value = settingsBundle
    }

    private fun insertNotes(notes: List<NoteEntity>) {
        viewModelScope.launch {
            notesUseCases.insertNotes(notes)
        }
    }
}