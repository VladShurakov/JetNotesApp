package com.vladshurakov.jetnotesapp.feature_settings.domain.usecase

import com.vladshurakov.jetnotesapp.feature_settings.domain.models.SettingsBundle
import com.vladshurakov.jetnotesapp.feature_settings.domain.repository.SettingsRepository

class SaveSettings (
    private val repository: SettingsRepository
) {

    operator fun invoke(settingsBundle: SettingsBundle) {
        repository.save(settingsBundle)
    }
}