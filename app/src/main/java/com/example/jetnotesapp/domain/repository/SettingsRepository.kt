package com.example.jetnotesapp.domain.repository

import com.example.jetnotesapp.domain.models.SettingsBundle

interface SettingsRepository {

    fun getSettings(): SettingsBundle

    fun saveSettings(settingsBundle: SettingsBundle)
}