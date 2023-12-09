package com.vladshurakov.jetnotesapp.feature_notes.presenter.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vladshurakov.jetnotesapp.R
import com.vladshurakov.jetnotesapp.feature_notes.presenter.components.SwipeToDismissNote
import com.vladshurakov.jetnotesapp.feature_notes.presenter.viewmodel.ArchivedViewModel
import com.vladshurakov.jetnotesapp.feature_notes.presenter.viewmodel.events.ArchivedEvent
import com.vladshurakov.jetnotesapp.feature_settings.presenter.components.ArchivedNotesTopBar
import com.vladshurakov.jetnotesapp.theme.MainTheme
import com.vladshurakov.jetnotesapp.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArchivedScreen(
    navController: NavController,
    archivedViewModel: ArchivedViewModel = hiltViewModel()
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
            items(
                items = archivedViewModel.state.value,
                key = { archivedNote -> archivedNote.id.hashCode() }
            ) { archivedNote ->
                val currentNote by rememberUpdatedState(archivedNote)
                val dismissState = rememberDismissState(
                    confirmValueChange = {
                        if (it == DismissValue.DismissedToStart) {
                            archivedViewModel.onEvent(ArchivedEvent.Delete(currentNote))
                        }
                        else if (it == DismissValue.DismissedToEnd) {
                            archivedViewModel.onEvent(ArchivedEvent.Unarchive(currentNote))
                        }
                        true
                    },
                    positionalThreshold = { 150.dp.toPx() }
                )

                SwipeToDismissNote(
                    note = currentNote,
                    onClick = {
                        navController.navigate(Screen.AddEditNote.route + "?id=${currentNote.id}")
                    },
                    dismissState = dismissState,
                    starDrawable = R.drawable.ic_unarchive,
                )
            }
        }
    }
}