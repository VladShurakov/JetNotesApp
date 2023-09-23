package com.example.notes.ui.screens.deleted_notes

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notes.R
import com.example.notes.ui.screens.deleted_notes.components.DeleteAlertDialog
import com.example.notes.ui.screens.deleted_notes.components.DeletedNoteView
import com.example.notes.ui.screens.deleted_notes.components.DeletedNotesTopAppBar
import com.example.notes.ui.theme.MainTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeletedNotesScreen(
    navController: NavController,
    viewModel: DeletedNotesViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val isDeleteAlertDialogOpen = remember {
        mutableStateOf(false)
    }
    val toastText = stringResource(id = R.string.cant_open_deleted_notes)

    if (isDeleteAlertDialogOpen.value) {
        DeleteAlertDialog(
            onCancel = {
                isDeleteAlertDialogOpen.value = false
            },
            viewModel = viewModel
        )
    }

    Scaffold(
        topBar = {
            DeletedNotesTopAppBar(
                onBack = {
                    navController.navigateUp()
                },
                onDeleteAll = {
                    isDeleteAlertDialogOpen.value = true
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MainTheme.colors.primaryBackground)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 16.dp)
            ) {
                items(state.deletedNotes) { deletedNote ->
                    DeletedNoteView(
                        title = deletedNote.title,
                        content = deletedNote.content,
                        timestamp = deletedNote.timestamp,
                        onClick = {
                            Toast.makeText(
                                navController.context.applicationContext,
                                toastText,
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        onDelete = {
                            viewModel.onEvent(DeletedNotesEvent.DeleteNote(deletedNote))
                        },
                        onRecover = {
                            viewModel.onEvent(DeletedNotesEvent.RecoverNote(deletedNote))
                        }
                    )
                }
            }
        }
    }
}