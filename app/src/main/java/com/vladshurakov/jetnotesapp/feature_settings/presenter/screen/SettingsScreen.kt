package com.vladshurakov.jetnotesapp.feature_settings.presenter.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vladshurakov.jetnotesapp.R
import com.vladshurakov.jetnotesapp.feature_settings.domain.models.SettingsBundle
import com.vladshurakov.jetnotesapp.feature_settings.presenter.components.SettingsCornerStyleDialog
import com.vladshurakov.jetnotesapp.feature_settings.presenter.components.SettingsStyleDialog
import com.vladshurakov.jetnotesapp.feature_settings.presenter.components.SettingsTextSizeDialog
import com.vladshurakov.jetnotesapp.feature_settings.presenter.components.SettingsThemeDialog
import com.vladshurakov.jetnotesapp.feature_settings.presenter.components.SettingsTopBar
import com.vladshurakov.jetnotesapp.feature_settings.presenter.components.SettingsView
import com.vladshurakov.jetnotesapp.theme.MainTheme
import com.vladshurakov.jetnotesapp.theme.MainTheme.colors
import com.vladshurakov.jetnotesapp.util.Screen

@Composable
fun SettingsScreen(
    navController: NavController,
    settingsBundle: SettingsBundle,
    onSettingsChanged: (SettingsBundle) -> Unit
) {

    val isStyleDialogOpen = remember {
        mutableStateOf(false)
    }
    val isTextSizeDialogOpen = remember {
        mutableStateOf(false)
    }
    val isCornerStyleDialogOpen = remember {
        mutableStateOf(false)
    }
    val isThemeDialogOpen = remember {
        mutableStateOf(false)
    }

    if (isStyleDialogOpen.value) {
        SettingsStyleDialog(
            onCancel = {
                isStyleDialogOpen.value = false
            },
            settingsBundle = settingsBundle,
            onSettingsChanged = onSettingsChanged
        )
    }

    if (isTextSizeDialogOpen.value) {
        SettingsTextSizeDialog(
            onCancel = {
                isTextSizeDialogOpen.value = false
            },
            settingsBundle = settingsBundle,
            onSettingsChanged = onSettingsChanged
        )
    }

    if (isCornerStyleDialogOpen.value) {
        SettingsCornerStyleDialog(
            onCancel = {
                isCornerStyleDialogOpen.value = false
            },
            settingsBundle = settingsBundle,
            onSettingsChanged = onSettingsChanged
        )
    }

    if (isThemeDialogOpen.value) {
        SettingsThemeDialog(
            onCancel = {
                isThemeDialogOpen.value = false
            },
            settingsBundle = settingsBundle,
            onSettingsChanged = onSettingsChanged
        )
    }

    Scaffold(
        topBar = {
            SettingsTopBar(
                onBack = {
                    navController.navigateUp()
                }
            )
        }
    ) { topBarPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.primaryBackground)
                .padding(topBarPadding)
        ) {

            SettingsView(
                onClick = {
                    isStyleDialogOpen.value = true
                },
                title = stringResource(id = R.string.label_theme_color),
                selectionText = settingsBundle.style.name,
                selectionTextColor = colors.tintColor
            )

            SettingsView(
                onClick = {
                    isTextSizeDialogOpen.value = true
                },
                title = stringResource(id = R.string.label_text_size),
                selectionText = settingsBundle.size.name
            )

            SettingsView(
                onClick = {
                    isCornerStyleDialogOpen.value = true
                },
                title = stringResource(id = R.string.label_card_form),
                selectionText = settingsBundle.cornerStyle.name
            )

            SettingsView(
                onClick = {
                    isThemeDialogOpen.value = true
                },
                title = stringResource(id = R.string.label_app_theme),
                selectionText = settingsBundle.theme.name
            )

            TextButton(
                onClick = {
                    navController.navigate(Screen.DeletedNotes.route)
                },
                shape = RectangleShape,
                contentPadding = PaddingValues(start = 18.dp),
                modifier = Modifier
                    .defaultMinSize(minHeight = 48.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.label_deleted_notes),
                        style = MainTheme.typography.body,
                        color = colors.tintColor
                    )
                }
            }

            TextButton(
                onClick = {
                    navController.navigate(Screen.ArchivedNotes.route)
                },
                shape = RectangleShape,
                contentPadding = PaddingValues(start = 18.dp),
                modifier = Modifier
                    .defaultMinSize(minHeight = 48.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.archived_notes),
                        style = MainTheme.typography.body,
                        color = colors.tintColor
                    )
                }
            }

            Divider(
                thickness = 1.dp,
                color = colors.secondaryTextColor,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.label_about),
                style = MainTheme.typography.title,
                color = colors.secondaryTextColor,
                modifier = Modifier
                    .padding(start = 18.dp)
                    .defaultMinSize(minHeight = 48.dp)
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )

            TextButton(
                onClick = {
                    val url = "https://github.com/VladShurakov/JetNotesApp"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    navController.context.startActivity(intent)
                },
                shape = RectangleShape,
                contentPadding = PaddingValues(start = 18.dp),
                modifier = Modifier
                    .defaultMinSize(minHeight = 48.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.label_github),
                        style = MainTheme.typography.body,
                        color = colors.tintColor
                    )
                }
            }

            TextButton(
                onClick = {
                    val url = "https://sites.google.com/view/jetnotesapp"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    navController.context.startActivity(intent)
                },
                shape = RectangleShape,
                contentPadding = PaddingValues(start = 18.dp),
                modifier = Modifier
                    .defaultMinSize(minHeight = 48.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.privacy_policy),
                        style = MainTheme.typography.body,
                        color = colors.tintColor
                    )
                }
            }

            Text(
                text = stringResource(id = R.string.app_version),
                style = MainTheme.typography.body,
                color = colors.primaryTextColor,
                modifier = Modifier
                    .padding(start = 18.dp)
                    .defaultMinSize(minHeight = 48.dp)
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )
        }
    }
}