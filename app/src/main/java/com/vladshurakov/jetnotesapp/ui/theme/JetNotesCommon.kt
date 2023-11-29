package com.vladshurakov.jetnotesapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle

data class JetNotesColors(
    val primaryTextColor: Color,
    val primaryBackground: Color,
    val secondaryTextColor: Color,
    val secondaryBackground: Color,
    val invertColor: Color,
    val tintColor: Color,
)

data class JetNotesTypography(
    val header: TextStyle,
    val title: TextStyle,
    val body: TextStyle,
    val caption: TextStyle,
)

data class JetNotesShape(
    val cornersStyle: Shape,
)

object MainTheme {
    val colors: JetNotesColors
        @Composable
        get() = LocalJetNotesColors.current

    val typography: JetNotesTypography
        @Composable
        get() = LocalJetNotesTypography.current

    val shapes: JetNotesShape
        @Composable
        get() = LocalJetNotesShape.current
}

val LocalJetNotesColors = staticCompositionLocalOf<JetNotesColors> {
    error("No colors provided")
}

val LocalJetNotesTypography = staticCompositionLocalOf<JetNotesTypography> {
    error("No font provided")
}

val LocalJetNotesShape = staticCompositionLocalOf<JetNotesShape> {
    error("No shapes provided")
}