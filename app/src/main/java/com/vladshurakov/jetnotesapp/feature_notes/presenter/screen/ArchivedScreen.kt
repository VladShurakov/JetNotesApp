package com.vladshurakov.jetnotesapp.feature_notes.presenter.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vladshurakov.jetnotesapp.feature_notes.domain.models.Folder
import com.vladshurakov.jetnotesapp.feature_notes.presenter.components.NoteView
import com.vladshurakov.jetnotesapp.feature_notes.presenter.viewmodel.ArchivedViewModel
import com.vladshurakov.jetnotesapp.feature_notes.presenter.viewmodel.events.ArchivedEvent
import com.vladshurakov.jetnotesapp.feature_settings.presenter.components.ArchivedNotesTopBar
import com.vladshurakov.jetnotesapp.theme.MainTheme
import com.vladshurakov.jetnotesapp.util.Screen

@Composable
fun ArchivedScreen(
    navController: NavController,
    viewModel: ArchivedViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            ArchivedNotesTopBar(
                onBack = {
                    navController.navigateUp()
                }
            )
        }
    ) { padding ->
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(MainTheme.colors.primaryBackground)
                .padding(padding)
        ) {
            items(viewModel.state.value) { archivedNotes ->
                AnimatedVisibility(
                    visible = archivedNotes.folder.name == Folder.ARCHIVED.name,
                    exit = shrinkVertically() + fadeOut()
                ){
                    NoteView(
                        title = archivedNotes.title,
                        content = archivedNotes.content,
                        timestamp = archivedNotes.timestamp,
                        onClick = {
                            navController.navigate(
                                Screen.AddEditNote.route + "?id=${archivedNotes.id}"
                            )
                        },
                        onUnarchive = {
                            viewModel.onEvent(ArchivedEvent.Unarchive(archivedNotes))
                        },
                        onDelete = {
                            viewModel.onEvent(ArchivedEvent.Delete(archivedNotes))
                        },
                    )
                }
            }
        }
    }
}