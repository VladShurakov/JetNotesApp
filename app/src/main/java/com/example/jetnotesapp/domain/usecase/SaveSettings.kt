package com.example.jetnotesapp.domain.usecase

import com.example.jetnotesapp.domain.models.SettingsBundle
import com.example.jetnotesapp.domain.repository.SettingsRepository

class SaveSettings (
    private val repository: SettingsRepository
) {

    operator fun invoke(settingsBundle: SettingsBundle) {
        repository.saveSettings(settingsBundle)
    }
}