package com.vladshurakov.jetnotesapp.feature_settings.presenter.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.vladshurakov.jetnotesapp.theme.MainTheme

@Composable
fun SettingsView(
    onClick: () -> Unit,
    title: String,
    selectionText: String,
    selectionTextColor: Color = MainTheme.colors.secondaryTextColor
) {
    Button(
        onClick = (onClick),
        colors = ButtonDefaults.textButtonColors(),
        shape = RectangleShape,
        contentPadding = PaddingValues(start = 16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 72.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            Text(
                text = title,
                style = MainTheme.typography.body,
                color = MainTheme.colors.primaryTextColor
            )

            Text(
                text = selectionText,
                style = MainTheme.typography.body,
                color = selectionTextColor
            )

        }
    }
}