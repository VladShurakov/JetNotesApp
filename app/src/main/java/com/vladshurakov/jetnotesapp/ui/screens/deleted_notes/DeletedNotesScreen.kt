package com.vladshurakov.jetnotesapp.ui.screens.deleted_notes

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vladshurakov.jetnotesapp.R
import com.vladshurakov.jetnotesapp.ui.components.DeletedNotesTopBar
import com.vladshurakov.jetnotesapp.ui.components.NoteView
import com.vladshurakov.jetnotesapp.ui.components.DeleteAlertDialog
import com.vladshurakov.jetnotesapp.ui.theme.MainTheme

@Composable
fun DeletedNotesScreen(
    navController: NavController,
    viewModel: DeletedNotesViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
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
            if (state.isEmpty()){

                item {

                    Text(
                        text = stringResource(id = R.string.label_no_deleted_note),
                        style = MainTheme.typography.title,
                        color = MainTheme.colors.primaryTextColor,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                }

            }else{

                item {

                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Icon(
                                painter = painterResource(id = R.drawable.recover),
                                contentDescription = stringResource(id = R.string.btn_recover),
                                tint = MainTheme.colors.invertColor
                            )

                            Text(
                                text = stringResource(id = R.string.btn_recover),
                                style = MainTheme.typography.caption,
                                color = MainTheme.colors.primaryTextColor
                            )

                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Icon(
                                painter = painterResource(id = R.drawable.delete),
                                contentDescription = stringResource(id = R.string.btn_delete),
                                tint = MainTheme.colors.invertColor
                            )

                            Text(
                                text = stringResource(id = R.string.btn_delete),
                                style = MainTheme.typography.caption,
                                color = MainTheme.colors.primaryTextColor
                            )

                        }

                    }

                }

                items(state) { deletedNote ->
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