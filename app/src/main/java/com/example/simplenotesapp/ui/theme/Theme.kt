package com.example.simplenotesapp.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simplenotesapp.domain.models.SettingsBundle
import com.example.simplenotesapp.domain.util.SimpleNotesCorners
import com.example.simplenotesapp.domain.util.SimpleNotesSize
import com.example.simplenotesapp.domain.util.SimpleNotesStyle

@Composable
fun SimpleNotesTheme(
    settingsBundle: SettingsBundle,
    content: @Composable () -> Unit
) {
    val colors = when (settingsBundle.isDarkMode) {
        true -> {
            when (settingsBundle.style) {
                SimpleNotesStyle.Red -> redDarkPalette
                SimpleNotesStyle.Yellow -> yellowDarkPalette
                SimpleNotesStyle.Green -> greenDarkPalette
                SimpleNotesStyle.Purple -> purpleDarkPalette
                SimpleNotesStyle.Blue -> blueDarkPalette
            }
        }

        false -> {
            when (settingsBundle.style) {
                SimpleNotesStyle.Red -> redLightPalette
                SimpleNotesStyle.Yellow -> yellowLightPalette
                SimpleNotesStyle.Green -> greenLightPalette
                SimpleNotesStyle.Purple -> purpleLightPalette
                SimpleNotesStyle.Blue -> blueLightPalette
            }
        }
    }

    val typography = SimpleNotesTypography(
        header = TextStyle(
            fontSize = when (settingsBundle.textSize) {
                SimpleNotesSize.Small -> 18.sp
                SimpleNotesSize.Normal -> 20.sp
                SimpleNotesSize.Big -> 22.sp
            },
            fontWeight = FontWeight.SemiBold
        ),
        title = TextStyle(
            fontSize = when (settingsBundle.textSize) {
                SimpleNotesSize.Small -> 16.sp
                SimpleNotesSize.Normal -> 18.sp
                SimpleNotesSize.Big -> 20.sp
            },
            fontWeight = FontWeight.Medium
        ),
        body = TextStyle(
            fontSize = when (settingsBundle.textSize) {
                SimpleNotesSize.Small -> 14.sp
                SimpleNotesSize.Normal -> 16.sp
                SimpleNotesSize.Big -> 18.sp
            },
            fontWeight = FontWeight.Normal
        ),
        caption = TextStyle(
            fontSize = when (settingsBundle.textSize) {
                SimpleNotesSize.Small -> 10.sp
                SimpleNotesSize.Normal -> 12.sp
                SimpleNotesSize.Big -> 14.sp
            },
            fontWeight = FontWeight.ExtraLight
        ),
    )

    val shapes = SimpleNotesShape(
        cornersStyle = when (settingsBundle.cornerStyle){
            SimpleNotesCorners.Rounded -> RoundedCornerShape(8.dp)
            SimpleNotesCorners.Flat -> RoundedCornerShape(0.dp)
        }
    )

    CompositionLocalProvider(
        LocalSimpleNotesColors provides colors,
        LocalSimpleNotesTypography provides typography,
        LocalSimpleNotesShape provides shapes,
        content = content
    )
}