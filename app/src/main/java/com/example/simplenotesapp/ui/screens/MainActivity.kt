package com.example.simplenotesapp.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.simplenotesapp.ui.screens.add_edit_note.AddEditNoteScreen
import com.example.simplenotesapp.ui.screens.deleted_notes.DeletedNotesScreen
import com.example.simplenotesapp.ui.screens.notes.NotesScreen
import com.example.simplenotesapp.ui.screens.settings.SettingsScreen
import com.example.simplenotesapp.ui.theme.SimpleNotesTheme
import com.example.simplenotesapp.ui.util.Screen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val mainViewModel = hiltViewModel<MainViewModel>()
            val navController = rememberNavController()
            val settingsBundle = remember {
                mutableStateOf(
                    mainViewModel.getSettings()
                )
            }

            SimpleNotesTheme(settingsBundle = settingsBundle.value) {
                NavHost(
                    navController = navController,
                    startDestination = Screen.Notes.route
                ) {

                    composable(route = Screen.Notes.route) {
                        NotesScreen(
                            navController = navController,
                            viewModel = mainViewModel
                        )
                    }

                    composable(
                        route = Screen.AddEditNote.route + "?id={id}",
                        arguments = listOf(navArgument(name = "id") {
                            type = NavType.LongType
                            defaultValue = -1
                        }
                        )
                    ) {
                        AddEditNoteScreen(
                            navController = navController
                        )
                    }

                    composable(route = Screen.DeletedNotes.route) {
                        DeletedNotesScreen(
                            navController = navController
                        )
                    }

                    composable(route = Screen.Settings.route) {
                        SettingsScreen(
                            navController = navController,
                            settingsBundle = settingsBundle.value,
                            viewModel = mainViewModel,
                            onSettingsChanged = {
                                settingsBundle.value = settingsBundle.value.copy(
                                    isDarkMode = it.isDarkMode,
                                    textSize = it.textSize,
                                    cornerStyle = it.cornerStyle,
                                    style = it.style,
                                    notesOrder = it.notesOrder
                                )
                                mainViewModel.saveSettings(settingsBundle.value)
                            }
                        )
                    }

                }
            }
        }
    }
}