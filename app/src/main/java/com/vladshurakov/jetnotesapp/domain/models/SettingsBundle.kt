package com.vladshurakov.jetnotesapp.domain.models

import com.vladshurakov.jetnotesapp.domain.util.JetNotesCorners
import com.vladshurakov.jetnotesapp.domain.util.JetNotesSize
import com.vladshurakov.jetnotesapp.domain.util.JetNotesStyle
import com.vladshurakov.jetnotesapp.domain.util.JetNotesTheme
import com.vladshurakov.jetnotesapp.domain.util.NotesOrder
import com.vladshurakov.jetnotesapp.domain.util.OrderType

data class SettingsBundle(
    val isDarkMode: Boolean = false,
    val theme: JetNotesTheme,
    val textSize: JetNotesSize,
    val cornerStyle: JetNotesCorners,
    val style: JetNotesStyle,
    val notesOrder: NotesOrder,
    val orderType: OrderType
)
