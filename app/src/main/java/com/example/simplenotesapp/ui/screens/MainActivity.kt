package com.example.simplenotesapp.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
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
import com.example.simplenotesapp.ui.screens.notes.NotesViewModel
import com.example.simplenotesapp.ui.screens.settings.SettingsBundle
import com.example.simplenotesapp.ui.screens.settings.SettingsScreen
import com.example.simplenotesapp.ui.theme.SimpleNotesCorners
import com.example.simplenotesapp.ui.theme.SimpleNotesSize
import com.example.simplenotesapp.ui.theme.SimpleNotesStyle
import com.example.simplenotesapp.ui.theme.SimpleNotesTheme
import com.example.simplenotesapp.ui.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val isDarkModeValue = isSystemInDarkTheme()

            val settingsBundle = remember{
                mutableStateOf(
                    SettingsBundle(
                        isDarkMode = isDarkModeValue,
                        textSize = SimpleNotesSize.Normal,
                        cornerStyle = SimpleNotesCorners.Rounded,
                        style = SimpleNotesStyle.Yellow
                    )
                )
            }

            SimpleNotesTheme(
                settingsBundle = settingsBundle.value
            ) {
                val navController = rememberNavController()
                val noteViewModel = hiltViewModel<NotesViewModel>()

                NavHost(navController = navController, startDestination = Screen.Notes.route) {
                    composable(Screen.Notes.route) {
                        NotesScreen(navController = navController, viewModel = noteViewModel)
                    }
                    composable(
                        Screen.AddEditNote.route + "?id={id}",
                        arguments = listOf(
                            navArgument(
                                name = "id"
                            ) {
                                type = NavType.LongType
                                defaultValue = -1
                            },
                        )
                    ) {

                        AddEditNoteScreen(navController = navController)
                    }
                    composable(Screen.DeletedNotes.route){
                        DeletedNotesScreen(navController = navController)
                    }
                    composable(Screen.Settings.route){
                        SettingsScreen(
                            navController = navController,
                            viewModel = noteViewModel,
                            settingsBundle = settingsBundle.value,
                            onSettingsChanged = {
                                settingsBundle.value = settingsBundle.value.copy(isDarkMode = it.isDarkMode)
                                settingsBundle.value = settingsBundle.value.copy(style = it.style)
                                settingsBundle.value = settingsBundle.value.copy(textSize = it.textSize)
                                settingsBundle.value = settingsBundle.value.copy(cornerStyle = it.cornerStyle)
                            }
                        )
                    }
                }

            }
        }
    }

}