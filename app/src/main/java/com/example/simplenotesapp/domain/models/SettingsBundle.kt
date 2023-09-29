package com.example.simplenotesapp.domain.models

import com.example.simplenotesapp.domain.util.NotesOrder
import com.example.simplenotesapp.domain.util.OrderType
import com.example.simplenotesapp.domain.util.SimpleNotesCorners
import com.example.simplenotesapp.domain.util.SimpleNotesSize
import com.example.simplenotesapp.domain.util.SimpleNotesStyle

data class SettingsBundle(

    val isDarkMode: Boolean,

    val textSize: SimpleNotesSize,

    val cornerStyle: SimpleNotesCorners,

    val style: SimpleNotesStyle,

    val notesOrder: NotesOrder,

    val orderType: OrderType
)
