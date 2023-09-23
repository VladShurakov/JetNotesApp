package com.example.simplenotesapp.ui.screens.deleted_notes.components

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
fun DeletedNotesTopAppBar(
    onBack: () -> Unit,
    onDeleteAll: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.deleted_notes),
                style = MainTheme.typography.header,
            )
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
                onClick = (onDeleteAll)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.delete_all),
                    contentDescription = stringResource(id = R.string.delete_all_notes),
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MainTheme.colors.primaryBackground,
            actionIconContentColor = MainTheme.colors.invertColor,
            navigationIconContentColor = MainTheme.colors.invertColor,
            titleContentColor = MainTheme.colors.primaryTextColor,
        )
    )
}