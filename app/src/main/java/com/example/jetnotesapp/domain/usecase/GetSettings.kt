package com.example.jetnotesapp.domain.usecase

import com.example.jetnotesapp.domain.models.SettingsBundle
import com.example.jetnotesapp.domain.repository.SettingsRepository

class GetSettings(
    private val repository: SettingsRepository
) {

    operator fun invoke(): SettingsBundle {
        return repository.getSettings()
    }
}