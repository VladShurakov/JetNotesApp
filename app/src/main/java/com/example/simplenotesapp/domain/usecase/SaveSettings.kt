package com.example.simplenotesapp.domain.usecase

import com.example.simplenotesapp.domain.models.SettingsBundle
import com.example.simplenotesapp.domain.repository.SettingsRepository

class SaveSettings (
    private val repository: SettingsRepository
) {

    operator fun invoke(settingsBundle: SettingsBundle) {
        repository.saveSettings(settingsBundle)
    }
}