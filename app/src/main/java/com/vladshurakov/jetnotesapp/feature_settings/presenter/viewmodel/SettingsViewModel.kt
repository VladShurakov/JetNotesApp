package com.vladshurakov.jetnotesapp.feature_settings.presenter.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.vladshurakov.jetnotesapp.feature_settings.domain.models.SettingsBundle
import com.vladshurakov.jetnotesapp.feature_settings.domain.usecase.SettingsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/*
 * ViewModel for Settings
 */
@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsUseCases: SettingsUseCases
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
}