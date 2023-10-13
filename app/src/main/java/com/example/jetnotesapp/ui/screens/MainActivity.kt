package com.example.jetnotesapp.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetnotesapp.domain.util.JetNotesTheme.Dark
import com.example.jetnotesapp.domain.util.JetNotesTheme.Default
import com.example.jetnotesapp.ui.screens.add_edit_note.AddEditNoteScreen
import com.example.jetnotesapp.ui.screens.deleted_notes.DeletedNotesScreen
import com.example.jetnotesapp.ui.screens.notes.NotesScreen
import com.example.jetnotesapp.ui.screens.settings.SettingsScreen
import com.example.jetnotesapp.ui.theme.JetNotesTheme
import com.example.jetnotesapp.ui.theme.darkPalette
import com.example.jetnotesapp.ui.theme.lightPalette
import com.example.jetnotesapp.ui.util.Screen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainViewModel = hiltViewModel<MainViewModel>()
            val settingsBundle = remember {
                mutableStateOf(
                    mainViewModel.getSettings()
                )
            }
            // System Theme if theme by Default
            if (settingsBundle.value.theme == Default) {
                settingsBundle.value = settingsBundle.value.copy(isDarkMode = isSystemInDarkTheme())
            }

            JetNotesTheme(settingsBundle = settingsBundle.value) {
                val navController = rememberNavController()
                val systemUiController = rememberSystemUiController()

                // Set status bar color
                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = if (settingsBundle.value.isDarkMode || settingsBundle.value.theme == Dark) darkPalette.primaryBackground
                        else lightPalette.primaryBackground
                    )
                }

                NavHost(
                    navController = navController, startDestination = Screen.Notes.route
                ) {
                    composable(route = Screen.Notes.route) {
                        NotesScreen(navController = navController, viewModel = mainViewModel)
                    }

                    composable(
                        route = Screen.AddEditNote.route + "?id={id}",
                        arguments = listOf(navArgument("id") {
                            type = NavType.LongType; defaultValue = -1
                        })
                    ) {
                        AddEditNoteScreen(navController = navController)
                    }

                    composable(route = Screen.DeletedNotes.route) {
                        DeletedNotesScreen(navController = navController)
                    }

                    composable(route = Screen.Settings.route) {
                        SettingsScreen(navController = navController,
                            settingsBundle = settingsBundle.value,
                            viewModel = mainViewModel,
                            onSettingsChanged = {
                                settingsBundle.value = settingsBundle.value.copy(
                                    isDarkMode = it.isDarkMode,
                                    theme = it.theme,
                                    textSize = it.textSize,
                                    cornerStyle = it.cornerStyle,
                                    style = it.style,
                                    notesOrder = it.notesOrder
                                )
                                mainViewModel.saveSettings(settingsBundle.value)
                            })
                    }

                }
            }
        }
    }
}