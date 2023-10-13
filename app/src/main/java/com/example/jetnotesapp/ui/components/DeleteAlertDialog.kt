package com.example.jetnotesapp.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetnotesapp.R
import com.example.jetnotesapp.ui.screens.deleted_notes.DeletedNotesEvent
import com.example.jetnotesapp.ui.screens.deleted_notes.DeletedNotesViewModel
import com.example.jetnotesapp.ui.theme.MainTheme

@Composable
fun DeleteAlertDialog(
    onCancel: () -> Unit,
    viewModel: DeletedNotesViewModel = hiltViewModel()
) {
    AlertDialog(
        onDismissRequest = (onCancel),
        confirmButton = {
            TextButton(
                onClick = {
                    viewModel.state.value.onEach {
                        viewModel.onEvent(DeletedNotesEvent.DeleteNote(it))
                    }
                    onCancel.invoke()
                }
            ) {
                Text(
                    text = stringResource(id = R.string.btn_delete),
                    style = MainTheme.typography.body,
                    color = MainTheme.colors.primaryTextColor
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = (onCancel)
            ) {
                Text(
                    text = stringResource(id = R.string.btn_cancel),
                    style = MainTheme.typography.body,
                    color = MainTheme.colors.primaryTextColor
                )
            }
        },
        text = {
            Text(
                text = stringResource(id = R.string.msg_delete_all_notes),
                style = MainTheme.typography.title,
                color = MainTheme.colors.primaryTextColor
            )
        },
        containerColor = MainTheme.colors.secondaryBackground,
        shape = MainTheme.shapes.cornersStyle
    )
}