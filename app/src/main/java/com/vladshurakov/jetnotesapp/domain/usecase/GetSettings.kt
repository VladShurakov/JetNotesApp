package com.vladshurakov.jetnotesapp.domain.usecase

import com.vladshurakov.jetnotesapp.domain.models.SettingsBundle
import com.vladshurakov.jetnotesapp.domain.repository.SettingsRepository

class GetSettings(
    private val repository: SettingsRepository
) {

    operator fun invoke(): SettingsBundle {
        return repository.getSettings()
    }
}