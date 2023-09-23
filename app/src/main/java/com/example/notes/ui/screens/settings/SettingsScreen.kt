package com.example.notes.ui.screens.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notes.R
import com.example.notes.ui.screens.notes.NotesViewModel
import com.example.notes.ui.screens.settings.components.SettingsCornerStyleDialog
import com.example.notes.ui.screens.settings.components.SettingsSortDialog
import com.example.notes.ui.screens.settings.components.SettingsStyleDialog
import com.example.notes.ui.screens.settings.components.SettingsTextSizeDialog
import com.example.notes.ui.screens.settings.components.SettingsTopBar
import com.example.notes.ui.screens.settings.components.SettingsView
import com.example.notes.ui.theme.MainTheme
import com.example.notes.ui.theme.MainTheme.colors
import com.example.notes.ui.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: NotesViewModel = hiltViewModel(),
    settingsBundle: SettingsBundle,
    onSettingsChanged: (SettingsBundle) -> Unit
) {

    val isSortDialogOpen = remember {
        mutableStateOf(false)
    }
    val isStyleDialogOpen = remember {
        mutableStateOf(false)
    }
    val isTextSizeDialogOpen = remember {
        mutableStateOf(false)
    }
    val isCornerStyleDialogOpen = remember {
        mutableStateOf(false)
    }

    if (isSortDialogOpen.value) {
        SettingsSortDialog(
            onCancel = {
                isSortDialogOpen.value = false
            },
            viewModel = viewModel
        )
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

    Scaffold(
        topBar = {
            SettingsTopBar(
                onBack = {
                    navController.navigateUp()
                }
            )
        }
    ) { topBarPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(topBarPadding)
                .background(colors.primaryBackground)
        ) {

            item {

                Text(
                    text = stringResource(id = R.string.appearance),
                    style = MainTheme.typography.title,
                    color = colors.tintColor,
                    modifier = Modifier.padding(start = 18.dp)
                )

                SettingsView(
                    onClick = {
                        isStyleDialogOpen.value = true
                    },
                    title = stringResource(id = R.string.theme_color),
                    selectionText = settingsBundle.style.name
                )

                SettingsView(
                    onClick = {
                        isTextSizeDialogOpen.value = true
                    },
                    title = stringResource(id = R.string.text_size),
                    selectionText = settingsBundle.textSize.name
                )

                SettingsView(
                    onClick = {
                        isSortDialogOpen.value = true
                    },
                    title = stringResource(id = R.string.order),
                    selectionText = stringResource(id = viewModel.state.value.notesOrder.name)
                )

                SettingsView(
                    onClick = {
                        isCornerStyleDialogOpen.value = true
                    },
                    title = stringResource(id = R.string.card_form),
                    selectionText = settingsBundle.cornerStyle.name
                )

                SettingsView(
                    onClick = {
                        onSettingsChanged(settingsBundle.copy(isDarkMode = !settingsBundle.isDarkMode))
                    },
                    title = stringResource(id = R.string.app_theme),
                    selectionText = if (settingsBundle.isDarkMode) "Dark theme" else "Light theme"
                )

                Spacer(modifier = Modifier.height(8.dp))

                Divider(
                    thickness = 1.dp,
                    color = colors.secondaryTextColor
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(id = R.string.another),
                    style = MainTheme.typography.title,
                    color = colors.tintColor,
                    modifier = Modifier.padding(start = 18.dp)
                )

                TextButton(
                    onClick = {
                        navController.navigate(Screen.DeletedNotes.route)
                    },
                    shape = RectangleShape,
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 52.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.deleted_notes),
                        style = MainTheme.typography.title,
                        color = colors.primaryTextColor
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Divider(
                    thickness = 1.dp,
                    color = colors.secondaryTextColor
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(id = R.string.about_app),
                    style = MainTheme.typography.title,
                    color = colors.tintColor,
                    modifier = Modifier.padding(start = 18.dp)
                )
                
                TextButton(
                    onClick = {
                        val url = "https://github.com/VladislavShurakov"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        navController.context.startActivity(intent)
                    },
                    shape = RectangleShape,
                    modifier = Modifier
                        .fillMaxSize()
                        .defaultMinSize(minHeight = 52.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.github_repository),
                        style = MainTheme.typography.title,
                        color = colors.primaryTextColor,
                    )
                }
                

            }

        }
    }
}