package com.vladshurakov.jetnotesapp.theme

import androidx.compose.ui.graphics.Color

val lightPalette = JetNotesColors(
    primaryTextColor = Color(0xFF292A2E),
    primaryBackground = Color(0xFFF7F7F7),
    secondaryTextColor = Color(0xFF777777),
    secondaryBackground = Color(0xFFDDE4E6),
    invertColor = Color(0xFF292A2E),
    tintColor = Color.Black
)

val darkPalette = JetNotesColors(
    primaryTextColor = Color.White,
    primaryBackground = Color(0xFF1E1F22),
    secondaryTextColor = Color(0xFFB1B1B1),
    secondaryBackground = Color(0xFF292A2E),
    invertColor = Color.White,
    tintColor = Color.White
)

val redLightPalette = lightPalette.copy(
    tintColor = Color(0xFFB53636)
)

val redDarkPalette = darkPalette.copy(
    tintColor = Color(0xFFFF7171)
)

val yellowLightPalette = lightPalette.copy(
    tintColor = Color(0xFFF4B731)
)

val yellowDarkPalette = darkPalette.copy(
    tintColor = Color(0xFFFFD970)
)

val greenLightPalette = lightPalette.copy(
    tintColor = Color(0xFF51D266)
)

val greenDarkPalette = darkPalette.copy(
    tintColor = Color(0xFF70FF7A)
)

val purpleLightPalette = lightPalette.copy(
    tintColor = Color(0xFF7751D2)
)

val purpleDarkPalette = darkPalette.copy(
    tintColor = Color(0xFFA270FF)
)

val blueLightPalette = lightPalette.copy(
    tintColor = Color(0xFF5182D2)
)

val blueDarkPalette = darkPalette.copy(
    tintColor = Color(0xFF70A0FF)
)