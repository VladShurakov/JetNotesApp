package com.vladshurakov.jetnotesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.vladshurakov.jetnotesapp.feature_notes.presenter.screen.EditScreen
import com.vladshurakov.jetnotesapp.feature_notes.presenter.screen.ArchivedScreen
import com.vladshurakov.jetnotesapp.feature_notes.presenter.screen.DeletedScreen
import com.vladshurakov.jetnotesapp.feature_notes.presenter.screen.NotesScreen
import com.vladshurakov.jetnotesapp.feature_settings.presenter.screen.SettingsScreen
import com.vladshurakov.jetnotesapp.feature_settings.presenter.viewmodel.SettingsEvent
import com.vladshurakov.jetnotesapp.feature_settings.presenter.viewmodel.SettingsViewModel
import com.vladshurakov.jetnotesapp.theme.JetNotesTheme
import com.vladshurakov.jetnotesapp.theme.darkPalette
import com.vladshurakov.jetnotesapp.theme.lightPalette
import com.vladshurakov.jetnotesapp.util.JetNotesTheme.Dark
import com.vladshurakov.jetnotesapp.util.JetNotesTheme.Default
import com.vladshurakov.jetnotesapp.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val settingsViewModel = hiltViewModel<SettingsViewModel>()
            var settingsBundle by remember {
                mutableStateOf(settingsViewModel.settingsBundle.value)
            }

            // System Theme if theme by Default
            if (settingsBundle.theme == Default) {
                settingsBundle = settingsBundle.copy(isDarkMode = isSystemInDarkTheme())
            }

            JetNotesTheme(settingsBundle = settingsBundle) {
                val navController = rememberNavController()
                val systemUiController = rememberSystemUiController()

                // Set status bar color
                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = when {
                            settingsBundle.isDarkMode || settingsBundle.theme == Dark -> darkPalette.primaryBackground
                            else -> lightPalette.primaryBackground
                        }
                    )
                }

                NavHost(
                    navController = navController,
                    startDestination = Screen.Notes.route,
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None }
                ) {
                    composable(route = Screen.Notes.route) {
                        NotesScreen(navController = navController)
                    }

                    composable(
                        route = Screen.AddEditNote.route + "?id={id}",
                        arguments = listOf(navArgument("id") {
                            type = NavType.LongType; defaultValue = -1
                        })
                    ) {
                        EditScreen(navController = navController)
                    }

                    composable(route = Screen.DeletedNotes.route) {
                        DeletedScreen(navController = navController)
                    }

                    composable(route = Screen.ArchivedNotes.route) {
                        ArchivedScreen(navController = navController)
                    }

                    composable(route = Screen.Settings.route) {
                        SettingsScreen(
                            navController = navController,
                            settingsBundle = settingsBundle,
                            onSettingsChanged = { newSettingsBundle ->
                                settingsBundle = newSettingsBundle
                                settingsViewModel.onEvent(SettingsEvent.SaveSettings(settingsBundle))
                            }
                        )
                    }
                }
            }
        }
    }
}