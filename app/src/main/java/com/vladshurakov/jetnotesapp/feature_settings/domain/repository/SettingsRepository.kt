package com.vladshurakov.jetnotesapp.feature_settings.domain.repository

import com.vladshurakov.jetnotesapp.feature_settings.domain.models.SettingsBundle

interface SettingsRepository {
    fun get(): SettingsBundle
    fun save(settingsBundle: SettingsBundle)
}