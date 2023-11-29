package com.vladshurakov.jetnotesapp.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vladshurakov.jetnotesapp.domain.models.SettingsBundle
import com.vladshurakov.jetnotesapp.domain.util.JetNotesCorners
import com.vladshurakov.jetnotesapp.domain.util.JetNotesSize
import com.vladshurakov.jetnotesapp.domain.util.JetNotesStyle
import com.vladshurakov.jetnotesapp.domain.util.JetNotesTheme.Dark
import com.vladshurakov.jetnotesapp.domain.util.JetNotesTheme.Default
import com.vladshurakov.jetnotesapp.domain.util.JetNotesTheme.Light

@Composable
fun JetNotesTheme(
    settingsBundle: SettingsBundle,
    content: @Composable () -> Unit
) {
    val colors = when (settingsBundle.theme) {
        Default -> {
            when (settingsBundle.isDarkMode) {
                true -> {
                    when (settingsBundle.style) {
                        JetNotesStyle.Red -> redDarkPalette
                        JetNotesStyle.Yellow -> yellowDarkPalette
                        JetNotesStyle.Green -> greenDarkPalette
                        JetNotesStyle.Purple -> purpleDarkPalette
                        JetNotesStyle.Blue -> blueDarkPalette
                    }
                }
                false -> {
                    when (settingsBundle.style) {
                        JetNotesStyle.Red -> redLightPalette
                        JetNotesStyle.Yellow -> yellowLightPalette
                        JetNotesStyle.Green -> greenLightPalette
                        JetNotesStyle.Purple -> purpleLightPalette
                        JetNotesStyle.Blue -> blueLightPalette
                    }
                }
            }
        }
        Light -> {
            when (settingsBundle.style) {
                JetNotesStyle.Red -> redLightPalette
                JetNotesStyle.Yellow -> yellowLightPalette
                JetNotesStyle.Green -> greenLightPalette
                JetNotesStyle.Purple -> purpleLightPalette
                JetNotesStyle.Blue -> blueLightPalette
            }
        }
        Dark -> {
            when (settingsBundle.style) {
                JetNotesStyle.Red -> redDarkPalette
                JetNotesStyle.Yellow -> yellowDarkPalette
                JetNotesStyle.Green -> greenDarkPalette
                JetNotesStyle.Purple -> purpleDarkPalette
                JetNotesStyle.Blue -> blueDarkPalette
            }
        }
    }

    val typography = JetNotesTypography(
        header = TextStyle(
            fontSize = when (settingsBundle.textSize) {
                JetNotesSize.Small -> 18.sp
                JetNotesSize.Medium -> 20.sp
                JetNotesSize.Big -> 22.sp
            },
            fontWeight = FontWeight.Bold
        ),
        title = TextStyle(
            fontSize = when (settingsBundle.textSize) {
                JetNotesSize.Small -> 16.sp
                JetNotesSize.Medium -> 18.sp
                JetNotesSize.Big -> 20.sp
            },
            fontWeight = FontWeight.SemiBold
        ),
        body = TextStyle(
            fontSize = when (settingsBundle.textSize) {
                JetNotesSize.Small -> 14.sp
                JetNotesSize.Medium -> 16.sp
                JetNotesSize.Big -> 18.sp
            },
            fontWeight = FontWeight.Medium
        ),
        caption = TextStyle(
            fontSize = when (settingsBundle.textSize) {
                JetNotesSize.Small -> 12.sp
                JetNotesSize.Medium -> 14.sp
                JetNotesSize.Big -> 16.sp
            },
            fontWeight = FontWeight.Normal
        ),
    )

    val shapes = JetNotesShape(
        cornersStyle = when (settingsBundle.cornerStyle){
            JetNotesCorners.Rounded -> RoundedCornerShape(12.dp)
            JetNotesCorners.Flat -> RoundedCornerShape(0.dp)
        }
    )

    CompositionLocalProvider(
        LocalJetNotesColors provides colors,
        LocalJetNotesTypography provides typography,
        LocalJetNotesShape provides shapes,
        content = content
    )
}