package com.vladshurakov.jetnotesapp.feature_settings.presenter.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.vladshurakov.jetnotesapp.R
import com.vladshurakov.jetnotesapp.feature_notes.presenter.viewmodel.DeletedViewModel
import com.vladshurakov.jetnotesapp.feature_notes.presenter.viewmodel.events.DeletedEvent
import com.vladshurakov.jetnotesapp.theme.MainTheme

@Composable
fun DeleteAlertDialog(
    onCancel: () -> Unit,
    viewModel: DeletedViewModel = hiltViewModel()
) {
    AlertDialog(
        onDismissRequest = (onCancel),
        confirmButton = {
            TextButton(
                onClick = {
                    viewModel.state.value.onEach {
                        viewModel.onEvent(DeletedEvent.Delete(it))
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