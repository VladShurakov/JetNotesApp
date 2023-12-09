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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vladshurakov.jetnotesapp.R
import com.vladshurakov.jetnotesapp.feature_notes.presenter.components.SwipeToDismissNote
import com.vladshurakov.jetnotesapp.feature_notes.presenter.viewmodel.DeletedViewModel
import com.vladshurakov.jetnotesapp.feature_notes.presenter.viewmodel.events.DeletedEvent
import com.vladshurakov.jetnotesapp.feature_settings.presenter.components.DeleteAlertDialog
import com.vladshurakov.jetnotesapp.feature_settings.presenter.components.DeletedNotesTopBar
import com.vladshurakov.jetnotesapp.theme.MainTheme
import com.vladshurakov.jetnotesapp.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeletedScreen(
    navController: NavController,
    deletedViewModel: DeletedViewModel = hiltViewModel()
) {
    val isDeleteAlertDialogOpen = remember {
        mutableStateOf(false)
    }

    if (isDeleteAlertDialogOpen.value) {
        DeleteAlertDialog(
            onCancel = {
                isDeleteAlertDialogOpen.value = false
            },
            viewModel = deletedViewModel
        )
    }

    Scaffold(
        topBar = {
            DeletedNotesTopBar(
                onBack = {
                    navController.navigateUp()
                },
                onDeleteAll = {
                    isDeleteAlertDialogOpen.value = true
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
                items = deletedViewModel.state.value,
                key = { deletedNote -> deletedNote.id.hashCode() }
            ) { deletedNote ->
                val currentNote by rememberUpdatedState(deletedNote)
                val dismissState = rememberDismissState(
                    confirmValueChange = {
                        if (it == DismissValue.DismissedToStart) {
                            deletedViewModel.onEvent(DeletedEvent.Delete(currentNote))
                        }
                        else if (it == DismissValue.DismissedToEnd) {
                            deletedViewModel.onEvent(DeletedEvent.Restore(currentNote))
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
                    starDrawable = R.drawable.ic_restore,
                )
            }
        }
    }
}