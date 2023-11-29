package com.vladshurakov.jetnotesapp.domain.usecase

import com.vladshurakov.jetnotesapp.domain.models.SettingsBundle
import com.vladshurakov.jetnotesapp.domain.repository.SettingsRepository

class SaveSettings (
    private val repository: SettingsRepository
) {

    operator fun invoke(settingsBundle: SettingsBundle) {
        repository.saveSettings(settingsBundle)
    }
}