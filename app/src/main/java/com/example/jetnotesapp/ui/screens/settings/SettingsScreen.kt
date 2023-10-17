package com.example.jetnotesapp.ui.screens.settings

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
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetnotesapp.R
import com.example.jetnotesapp.domain.models.SettingsBundle
import com.example.jetnotesapp.ui.components.SettingsCornerStyleDialog
import com.example.jetnotesapp.ui.components.SettingsSortDialog
import com.example.jetnotesapp.ui.components.SettingsStyleDialog
import com.example.jetnotesapp.ui.components.SettingsTextSizeDialog
import com.example.jetnotesapp.ui.components.SettingsThemeDialog
import com.example.jetnotesapp.ui.components.SettingsTopBar
import com.example.jetnotesapp.ui.components.SettingsView
import com.example.jetnotesapp.ui.screens.MainViewModel
import com.example.jetnotesapp.ui.theme.MainTheme
import com.example.jetnotesapp.ui.theme.MainTheme.colors
import com.example.jetnotesapp.ui.util.Screen

@Composable
fun SettingsScreen(
    navController: NavController,
    settingsBundle: SettingsBundle,
    viewModel: MainViewModel = hiltViewModel(),
    onSettingsChanged: (SettingsBundle) -> Unit
) {

    val isStyleDialogOpen = remember {
        mutableStateOf(false)
    }
    val isTextSizeDialogOpen = remember {
        mutableStateOf(false)
    }
    val isSortDialogOpen = remember {
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

    if (isSortDialogOpen.value) {
        SettingsSortDialog(
            onCancel = {
                isSortDialogOpen.value = false
            },
            viewModel = viewModel
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
                selectionText = settingsBundle.textSize.name
            )

            SettingsView(
                onClick = {
                    isSortDialogOpen.value = true
                },
                title = stringResource(id = R.string.label_order),
                selectionText = stringResource(id = viewModel.notesState.value.notesOrder.stringName)
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

            Divider(
                thickness = 1.dp,
                color = colors.secondaryTextColor
            )

            Text(
                text = stringResource(id = R.string.label_about),
                style = MainTheme.typography.title,
                color = colors.secondaryTextColor,
                modifier = Modifier
                    .padding(start = 18.dp, top = 18.dp)
            )

            TextButton(
                onClick = {
                    val url = "https://github.com/VladShurakov/SimpleNotesApp"
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
                        text = stringResource(id = R.string.label_github_repository),
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
                text = "Version " + stringResource(id = R.string.app_version),
                style = MainTheme.typography.body,
                color = colors.primaryTextColor,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .defaultMinSize(minHeight = 48.dp)
                    .padding(start = 18.dp)
            )

        }
    }
}