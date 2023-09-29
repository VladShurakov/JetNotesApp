package com.example.simplenotesapp.domain.usecase

import com.example.simplenotesapp.domain.repository.SettingsRepository
import com.example.simplenotesapp.domain.models.SettingsBundle

class GetSettings(
    private val repository: SettingsRepository
) {

    operator fun invoke(): SettingsBundle {
        return repository.getSettings()
    }
}