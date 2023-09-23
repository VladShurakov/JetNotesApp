package com.example.simplenotesapp.ui.screens.add_edit_note.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.simplenotesapp.R
import com.example.simplenotesapp.ui.theme.MainTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditNoteTopAppBar(
    onBack: () -> Unit,
    onSave: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = "")
        },
        navigationIcon = {
            IconButton(
                onClick = (onBack)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_left),
                    contentDescription = stringResource(id = R.string.back),
                )
            }
        },
        actions = {
            IconButton(
                onClick = (onSave)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.save),
                    contentDescription = stringResource(id = R.string.save_note),
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MainTheme.colors.primaryBackground,
            actionIconContentColor = MainTheme.colors.invertColor,
            navigationIconContentColor = MainTheme.colors.invertColor,
        )
    )
}