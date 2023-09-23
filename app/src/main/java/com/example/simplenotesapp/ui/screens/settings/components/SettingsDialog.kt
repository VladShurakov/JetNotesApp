package com.example.simplenotesapp.ui.screens.settings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.simplenotesapp.R
import com.example.simplenotesapp.ui.screens.settings.SettingsBundle
import com.example.simplenotesapp.ui.theme.MainTheme
import com.example.simplenotesapp.ui.theme.SimpleNotesCorners
import com.example.simplenotesapp.ui.theme.SimpleNotesSize
import com.example.simplenotesapp.ui.theme.SimpleNotesStyle

@Composable
fun SettingsDialog(
    onCancel: () -> Unit,
    onValue: @Composable () -> Unit
) {
    Dialog(
        onDismissRequest = (onCancel)
    ) {
        Column {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MainTheme.colors.secondaryBackground
                ),
                shape = MainTheme.shapes.cornersStyle
            ) {

                Spacer(modifier = Modifier.height(12.dp))

                onValue.invoke()

                TextButton(
                    onClick = (onCancel),
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.cancel),
                        style = MainTheme.typography.body,
                        color = MainTheme.colors.primaryTextColor
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

            }
        }
    }
}

@Composable
fun SettingsCornerStyleDialog(
    onCancel: () -> Unit,
    settingsBundle: SettingsBundle,
    onSettingsChanged: (SettingsBundle) -> Unit,
){
    SettingsDialog(
        onCancel = (onCancel),
        onValue = {
            SimpleNotesCorners.values().onEach { notesCorner ->
                Button(
                    onClick = {
                        onSettingsChanged(settingsBundle.copy(cornerStyle = notesCorner)).also {
                            onCancel.invoke()
                        }
                    },
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color.Unspecified
                    ),
                    shape = RectangleShape,
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 52.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Icon(
                            painter = painterResource(
                                id = if (notesCorner == settingsBundle.cornerStyle) {
                                    R.drawable.filled_circle
                                } else {
                                    R.drawable.outline_circle
                                }
                            ),
                            contentDescription = stringResource(R.string.select_item),
                            tint = MainTheme.colors.invertColor,
                            modifier = Modifier.size(16.dp)
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = notesCorner.name,
                            style = MainTheme.typography.title,
                            color = MainTheme.colors.primaryTextColor
                        )

                    }
                }
            }
        }
    )
}

@Composable
fun SettingsStyleDialog(
    onCancel: () -> Unit,
    settingsBundle: SettingsBundle,
    onSettingsChanged: (SettingsBundle) -> Unit,
){
    SettingsDialog(
        onCancel = (onCancel),
        onValue = {
            SimpleNotesStyle.values().onEach { simpleNotesStyle ->
                Button(
                    onClick = {
                        onSettingsChanged(settingsBundle.copy(
                            style = simpleNotesStyle
                        )).also {
                            onCancel.invoke()
                        }
                    },
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color.Unspecified
                    ),
                    shape = RectangleShape,
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 52.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Icon(
                            painter = painterResource(
                                id = if (simpleNotesStyle == settingsBundle.style) {
                                    R.drawable.filled_circle
                                } else {
                                    R.drawable.outline_circle
                                }

                            ),
                            contentDescription = stringResource(R.string.select_item),
                            tint = MainTheme.colors.invertColor,
                            modifier = Modifier.size(16.dp)
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = simpleNotesStyle.name,
                            style = MainTheme.typography.title,
                            color = MainTheme.colors.primaryTextColor
                        )

                    }
                }
            }
        }
    )
}

@Composable
fun SettingsTextSizeDialog(
    onCancel: () -> Unit,
    settingsBundle: SettingsBundle,
    onSettingsChanged: (SettingsBundle) -> Unit,
){
    SettingsDialog(
        onCancel = (onCancel),
        onValue = {
            SimpleNotesSize.values().onEach { simpleNotesSize ->
                Button(
                    onClick = {
                        onSettingsChanged(settingsBundle.copy(
                            textSize = simpleNotesSize
                        )).also {
                            onCancel.invoke()
                        }
                    },
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color.Unspecified
                    ),
                    shape = RectangleShape,
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 52.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Icon(
                            painter = painterResource(
                                id = if (simpleNotesSize == settingsBundle.textSize) {
                                    R.drawable.filled_circle
                                } else {
                                    R.drawable.outline_circle
                                }

                            ),
                            contentDescription = stringResource(R.string.select_item),
                            tint = MainTheme.colors.invertColor,
                            modifier = Modifier.size(16.dp)
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = simpleNotesSize.name,
                            style = MainTheme.typography.title,
                            color = MainTheme.colors.primaryTextColor
                        )

                    }
                }
            }
        }
    )
}
