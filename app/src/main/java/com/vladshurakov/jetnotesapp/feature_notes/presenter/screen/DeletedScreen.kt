package com.vladshurakov.jetnotesapp.feature_notes.presenter.screen

import android.widget.Toast
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vladshurakov.jetnotesapp.R
import com.vladshurakov.jetnotesapp.feature_notes.domain.models.Folder
import com.vladshurakov.jetnotesapp.feature_notes.presenter.components.NoteView
import com.vladshurakov.jetnotesapp.feature_notes.presenter.viewmodel.DeletedViewModel
import com.vladshurakov.jetnotesapp.feature_notes.presenter.viewmodel.events.DeletedEvent
import com.vladshurakov.jetnotesapp.feature_settings.presenter.components.DeleteAlertDialog
import com.vladshurakov.jetnotesapp.feature_settings.presenter.components.DeletedNotesTopBar
import com.vladshurakov.jetnotesapp.theme.MainTheme

@Composable
fun DeletedScreen(
    navController: NavController,
    viewModel: DeletedViewModel = hiltViewModel()
) {
    val isDeleteAlertDialogOpen = remember {
        mutableStateOf(false)
    }
    val toastText = stringResource(id = R.string.msg_recover_to_open)

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
            items(viewModel.state.value) { deletedNote ->
                AnimatedVisibility(
                    visible = deletedNote.folder.name == Folder.DELETED.name,
                    exit = shrinkVertically() + fadeOut()
                ){
                    NoteView(
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
                            viewModel.onEvent(DeletedEvent.Delete(deletedNote))
                        },
                        onRecover = {
                            viewModel.onEvent(DeletedEvent.Restore(deletedNote))
                        }
                    )
                }
            }
        }
    }
}