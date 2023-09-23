package com.example.simplenotesapp.ui.screens.settings.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.example.simplenotesapp.ui.theme.MainTheme

@Composable
fun SettingsView(
    onClick: () -> Unit,
    title: String,
    selectionText: String
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
                .fillMaxWidth()
        ) {

            Text(
                text = title,
                style = MainTheme.typography.body,
                color = MainTheme.colors.primaryTextColor
            )

            Text(
                text = selectionText,
                style = MainTheme.typography.body,
                color = MainTheme.colors.secondaryTextColor
            )

        }
    }
}