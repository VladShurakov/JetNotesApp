package com.example.simplenotesapp.domain.repository

import com.example.simplenotesapp.domain.models.SettingsBundle

interface SettingsRepository {

    fun getSettings(): SettingsBundle

    fun saveSettings(settingsBundle: SettingsBundle)

}