package com.example.simplenotesapp.ui.screens.settings

import com.example.simplenotesapp.ui.theme.SimpleNotesCorners
import com.example.simplenotesapp.ui.theme.SimpleNotesSize
import com.example.simplenotesapp.ui.theme.SimpleNotesStyle

data class SettingsBundle(
    val isDarkMode: Boolean,
    val textSize: SimpleNotesSize,
    val cornerStyle: SimpleNotesCorners,
    val style: SimpleNotesStyle
)
