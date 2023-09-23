package com.example.notes.ui.screens.settings

import com.example.notes.ui.theme.SimpleNotesCorners
import com.example.notes.ui.theme.SimpleNotesSize
import com.example.notes.ui.theme.SimpleNotesStyle

data class SettingsBundle(
    val isDarkMode: Boolean,
    val textSize: SimpleNotesSize,
    val cornerStyle: SimpleNotesCorners,
    val style: SimpleNotesStyle
)
