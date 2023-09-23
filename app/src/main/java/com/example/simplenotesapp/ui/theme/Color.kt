package com.example.simplenotesapp.ui.theme

import androidx.compose.ui.graphics.Color

val lightPalette = SimpleNotesColors(
    primaryTextColor = Color(0xFF292A2E),
    primaryBackground = Color(0xFFF7F7F7),
    secondaryTextColor = Color(0xFF777777),
    secondaryBackground = Color.White,
    invertColor = Color(0xFF292A2E),
    tintColor = Color.Black
)

val darkPalette = SimpleNotesColors(
    primaryTextColor = Color.White,
    primaryBackground = Color(0xFF1E1F22),
    secondaryTextColor = Color(0xFFB1B1B1),
    secondaryBackground = Color(0xFF292A2E),
    invertColor = Color.White,
    tintColor = Color.White
)

val redLightPalette = lightPalette.copy(
    tintColor = Color(0xFFE01A1A)
)

val redDarkPalette = darkPalette.copy(
    tintColor = Color(0xFFFF3838)
)

val yellowLightPalette = lightPalette.copy(
    tintColor = Color(0xFFF4B731)
)

val yellowDarkPalette = darkPalette.copy(
    tintColor = Color(0xFFF4B731)
)

val greenLightPalette = lightPalette.copy(
    tintColor = Color(0xFF2BB80F)
)

val greenDarkPalette = darkPalette.copy(
    tintColor = Color(0xFF2BB80F)
)

val purpleLightPalette = lightPalette.copy(
    tintColor = Color(0xFF8C57CB)
)

val purpleDarkPalette = darkPalette.copy(
    tintColor = Color(0xFFA97DDE)
)

val blueLightPalette = lightPalette.copy(
    tintColor = Color(0xFF578FCB)
)

val blueDarkPalette = darkPalette.copy(
    tintColor = Color(0xFF578FCB)
)