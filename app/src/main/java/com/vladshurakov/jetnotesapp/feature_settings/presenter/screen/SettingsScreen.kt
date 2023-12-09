package com.vladshurakov.jetnotesapp.feature_settings.presenter.screen

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vladshurakov.jetnotesapp.R
import com.vladshurakov.jetnotesapp.feature_notes.domain.models.Note
import com.vladshurakov.jetnotesapp.feature_settings.domain.models.SettingsBundle
import com.vladshurakov.jetnotesapp.feature_settings.presenter.components.SettingsCornerStyleDialog
import com.vladshurakov.jetnotesapp.feature_settings.presenter.components.SettingsStyleDialog
import com.vladshurakov.jetnotesapp.feature_settings.presenter.components.SettingsTextSizeDialog
import com.vladshurakov.jetnotesapp.feature_settings.presenter.components.SettingsThemeDialog
import com.vladshurakov.jetnotesapp.feature_settings.presenter.components.SettingsTopBar
import com.vladshurakov.jetnotesapp.feature_settings.presenter.components.SettingsView
import com.vladshurakov.jetnotesapp.feature_settings.presenter.viewmodel.SettingsViewModel
import com.vladshurakov.jetnotesapp.theme.MainTheme
import com.vladshurakov.jetnotesapp.theme.MainTheme.colors
import com.vladshurakov.jetnotesapp.util.Screen
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun SettingsScreen(
    navController: NavController,
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    onSettingsChanged: (SettingsBundle) -> Unit,
) {

    val exportDataLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.CreateDocument()
    ) { uri ->
        if (uri == null) return@rememberLauncherForActivityResult
        val jsonString = Gson().toJson(settingsViewModel.getAll())
        navController.context.contentResolver.openOutputStream(uri).use {
            it?.bufferedWriter()?.apply {
                write(jsonString)
                flush()
                close()
            }
        }
    }

    val importDataLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) { uri ->
        if (uri == null) return@rememberLauncherForActivityResult
        val jsonString = navController.context.contentResolver.openInputStream(uri).use {
            it?.bufferedReader()?.readText() ?: "[]"
        }
        val type: Type = object : TypeToken<List<Note>>() {}.type
        val notes: MutableList<Note> =
            Gson().fromJson<List<Note>?>(jsonString, type).toMutableList()
        notes.forEachIndexed { index, note ->
            notes[index] = note.copy(id = null)
        }
        settingsViewModel.insert(notes)
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
    val isThemeDialogOpen = remember {
        mutableStateOf(false)
    }

    if (isStyleDialogOpen.value) {
        SettingsStyleDialog(
            onCancel = {
                isStyleDialogOpen.value = false
            },
            settingsBundle = settingsViewModel.settingsBundle.value,
            onSettingsChanged = onSettingsChanged
        )
    }

    if (isTextSizeDialogOpen.value) {
        SettingsTextSizeDialog(
            onCancel = {
                isTextSizeDialogOpen.value = false
            },
            settingsBundle = settingsViewModel.settingsBundle.value,
            onSettingsChanged = onSettingsChanged
        )
    }

    if (isCornerStyleDialogOpen.value) {
        SettingsCornerStyleDialog(
            onCancel = {
                isCornerStyleDialogOpen.value = false
            },
            settingsBundle = settingsViewModel.settingsBundle.value,
            onSettingsChanged = onSettingsChanged
        )
    }

    if (isThemeDialogOpen.value) {
        SettingsThemeDialog(
            onCancel = {
                isThemeDialogOpen.value = false
            },
            settingsBundle = settingsViewModel.settingsBundle.value,
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
                .background(colors.primaryBackground)
                .padding(topBarPadding)
        ) {

            item {

                Text(
                    text = stringResource(R.string.appearance),
                    style = MainTheme.typography.title,
                    color = colors.primaryTextColor,
                    modifier = Modifier
                        .padding(start = 18.dp)
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )

                SettingsView(
                    onClick = {
                        isStyleDialogOpen.value = true
                    },
                    title = stringResource(id = R.string.label_theme_color),
                    selectionText = settingsViewModel.settingsBundle.value.style.name,
                    selectionTextColor = colors.tintColor
                )

                SettingsView(
                    onClick = {
                        isTextSizeDialogOpen.value = true
                    },
                    title = stringResource(id = R.string.label_text_size),
                    selectionText = settingsViewModel.settingsBundle.value.size.name
                )

                SettingsView(
                    onClick = {
                        isCornerStyleDialogOpen.value = true
                    },
                    title = stringResource(id = R.string.label_card_form),
                    selectionText = settingsViewModel.settingsBundle.value.cornerStyle.name
                )

                SettingsView(
                    onClick = {
                        isThemeDialogOpen.value = true
                    },
                    title = stringResource(id = R.string.label_app_theme),
                    selectionText = settingsViewModel.settingsBundle.value.theme.name
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
                    text = stringResource(id = R.string.export_import_data),
                    style = MainTheme.typography.title,
                    color = colors.primaryTextColor,
                    modifier = Modifier
                        .padding(start = 18.dp, top = 12.dp)
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )

                TextButton(
                    onClick = {
                        val currentTime =
                            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
                        exportDataLauncher.launch("JetNotesBackup-${currentTime}.json")
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
                            text = "Export notes",
                            style = MainTheme.typography.body,
                            color = colors.tintColor
                        )
                    }
                }

                TextButton(
                    onClick = { importDataLauncher.launch(arrayOf("*/*")) },
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
                            text = "Import notes",
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
                    color = colors.primaryTextColor,
                    modifier = Modifier
                        .padding(start = 18.dp, top = 12.dp)
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )

                TextButton(
                    onClick = {
                        openLink(
                            navController.context,
                            "https://github.com/VladShurakov/JetNotesApp"
                        )
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
                        openLink(navController.context, "https://sites.google.com/view/jetnotesapp")
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

                val versionName = try {
                    navController.context.packageManager.getPackageInfo(
                        navController.context.packageName,
                        0
                    ).versionName
                } catch (e: Exception) {
                    ""
                }

                Text(
                    text = "Version $versionName",
                    style = MainTheme.typography.body,
                    color = colors.secondaryTextColor,
                    modifier = Modifier
                        .padding(start = 18.dp)
                        .defaultMinSize(minHeight = 48.dp)
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )

            }
        }
    }
}

private fun openLink(context: Context, link: String) {
    val uri = Uri.parse(link)
    val intent = Intent(Intent.ACTION_VIEW, uri)
    try {
        context.startActivity(intent)
    } catch (exception: ActivityNotFoundException) {
        Toast.makeText(
            context,
            R.string.install_a_browser,
            Toast.LENGTH_SHORT
        ).show()
    }
}