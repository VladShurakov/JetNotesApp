package com.example.jetnotesapp.domain.models

import com.example.jetnotesapp.domain.util.JetNotesCorners
import com.example.jetnotesapp.domain.util.JetNotesSize
import com.example.jetnotesapp.domain.util.JetNotesStyle
import com.example.jetnotesapp.domain.util.JetNotesTheme
import com.example.jetnotesapp.domain.util.NotesOrder
import com.example.jetnotesapp.domain.util.OrderType

data class SettingsBundle(
    val isDarkMode: Boolean = false,
    val theme: JetNotesTheme,
    val textSize: JetNotesSize,
    val cornerStyle: JetNotesCorners,
    val style: JetNotesStyle,
    val notesOrder: NotesOrder,
    val orderType: OrderType
)
