package com.vladshurakov.jetnotesapp.domain.repository

import com.vladshurakov.jetnotesapp.domain.models.SettingsBundle

interface SettingsRepository {

    fun getSettings(): SettingsBundle

    fun saveSettings(settingsBundle: SettingsBundle)
}