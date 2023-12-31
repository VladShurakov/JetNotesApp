package com.vladshurakov.jetnotesapp.feature_settings.presenter.components

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
import com.vladshurakov.jetnotesapp.R
import com.vladshurakov.jetnotesapp.feature_settings.domain.models.SettingsBundle
import com.vladshurakov.jetnotesapp.theme.MainTheme
import com.vladshurakov.jetnotesapp.util.JetNotesCorners
import com.vladshurakov.jetnotesapp.util.JetNotesSize
import com.vladshurakov.jetnotesapp.util.JetNotesStyle
import com.vladshurakov.jetnotesapp.util.JetNotesTheme

@Composable
fun CustomDialog(
    title: String,
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

                Text(
                    text = title,
                    style = MainTheme.typography.title,
                    color = MainTheme.colors.primaryTextColor,
                    modifier = Modifier
                        .padding(start = 16.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                onValue.invoke()

                TextButton(
                    onClick = (onCancel),
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.btn_cancel),
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
fun SettingsThemeDialog(
    onCancel: () -> Unit,
    settingsBundle: SettingsBundle,
    onSettingsChanged: (SettingsBundle) -> Unit,
) {
    CustomDialog(
        title = stringResource(id = R.string.label_app_theme),
        onCancel = (onCancel),
        onValue = {
            JetNotesTheme.values().onEach { theme ->
                Button(
                    onClick = {
                        if (theme != settingsBundle.theme) {
                            onSettingsChanged(settingsBundle.copy(theme = theme))
                        }
                        onCancel.invoke()
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
                                id = if (theme == settingsBundle.theme) {
                                    R.drawable.ic_circle_filled
                                } else {
                                    R.drawable.ic_circle_outline
                                }
                            ),
                            contentDescription = stringResource(R.string.btn_selected_item),
                            tint = MainTheme.colors.invertColor,
                            modifier = Modifier.size(16.dp)
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = theme.name,
                            style = MainTheme.typography.body,
                            color = MainTheme.colors.primaryTextColor,
                        )

                    }
                }
            }
        }
    )
}

@Composable
fun SettingsCornerStyleDialog(
    onCancel: () -> Unit,
    settingsBundle: SettingsBundle,
    onSettingsChanged: (SettingsBundle) -> Unit,
) {
    CustomDialog(
        title = stringResource(id = R.string.label_card_form),
        onCancel = (onCancel),
        onValue = {
            JetNotesCorners.values().onEach { notesCorner ->
                Button(
                    onClick = {
                        if (notesCorner != settingsBundle.cornerStyle) {
                            onSettingsChanged(settingsBundle.copy(cornerStyle = notesCorner))
                        }
                        onCancel.invoke()
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
                                    R.drawable.ic_circle_filled
                                } else {
                                    R.drawable.ic_circle_outline
                                }
                            ),
                            contentDescription = stringResource(R.string.btn_selected_item),
                            tint = MainTheme.colors.invertColor,
                            modifier = Modifier.size(16.dp)
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = notesCorner.name,
                            style = MainTheme.typography.body,
                            color = MainTheme.colors.primaryTextColor,
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
) {
    CustomDialog(
        title = stringResource(id = R.string.label_theme_color),
        onCancel = (onCancel),
        onValue = {
            JetNotesStyle.values().onEach { simpleNotesStyle ->
                Button(
                    onClick = {
                        if (simpleNotesStyle != settingsBundle.style) {
                            onSettingsChanged(settingsBundle.copy(style = simpleNotesStyle))
                        }
                        onCancel.invoke()
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
                                    R.drawable.ic_circle_filled
                                } else {
                                    R.drawable.ic_circle_outline
                                }

                            ),
                            contentDescription = stringResource(R.string.btn_selected_item),
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
) {
    CustomDialog(
        title = stringResource(id = R.string.label_text_size),
        onCancel = (onCancel),
        onValue = {
            JetNotesSize.values().onEach { simpleNotesSize ->
                Button(
                    onClick = {
                        if (simpleNotesSize != settingsBundle.size) {
                            onSettingsChanged(settingsBundle.copy(size = simpleNotesSize))
                        }
                        onCancel.invoke()
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
                                id = if (simpleNotesSize == settingsBundle.size) {
                                    R.drawable.ic_circle_filled
                                } else {
                                    R.drawable.ic_circle_outline
                                }

                            ),
                            contentDescription = stringResource(R.string.btn_selected_item),
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
