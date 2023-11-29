package com.vladshurakov.jetnotesapp.feature_settings.domain.models

import com.vladshurakov.jetnotesapp.util.JetNotesCorners
import com.vladshurakov.jetnotesapp.util.JetNotesSize
import com.vladshurakov.jetnotesapp.util.JetNotesStyle
import com.vladshurakov.jetnotesapp.util.JetNotesTheme
import com.vladshurakov.jetnotesapp.util.OrderType

data class SettingsBundle(
    val isDarkMode: Boolean = false,
    val theme: JetNotesTheme = JetNotesTheme.Default,
    val size: JetNotesSize = JetNotesSize.Medium,
    val cornerStyle: JetNotesCorners = JetNotesCorners.Rounded,
    val style: JetNotesStyle = JetNotesStyle.Red,
    val orderType: OrderType = OrderType.Descending
)