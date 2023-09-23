package com.example.notes.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle

data class SimpleNotesColors(
    val primaryTextColor: Color,
    val primaryBackground: Color,
    val secondaryTextColor: Color,
    val secondaryBackground: Color,
    val invertColor: Color,
    val tintColor: Color,
)

data class SimpleNotesTypography(
    val header: TextStyle,
    val title: TextStyle,
    val body: TextStyle,
    val caption: TextStyle,
)

data class SimpleNotesShape(
    val cornersStyle: Shape,
)

object MainTheme {
    val colors: SimpleNotesColors
        @Composable
        get() = LocalSimpleNotesColors.current

    val typography: SimpleNotesTypography
        @Composable
        get() = LocalSimpleNotesTypography.current

    val shapes: SimpleNotesShape
        @Composable
        get() = LocalSimpleNotesShape.current
}

enum class SimpleNotesStyle {
    Red, Yellow, Green, Purple, Blue
}

enum class SimpleNotesSize {
    Small, Normal, Big
}

enum class SimpleNotesCorners {
    Rectangular, Rounded
}

val LocalSimpleNotesColors = staticCompositionLocalOf<SimpleNotesColors> {
    error("No colors provided")
}

val LocalSimpleNotesTypography = staticCompositionLocalOf<SimpleNotesTypography> {
    error("No font provided")
}

val LocalSimpleNotesShape = staticCompositionLocalOf<SimpleNotesShape> {
    error("No shapes provided")
}