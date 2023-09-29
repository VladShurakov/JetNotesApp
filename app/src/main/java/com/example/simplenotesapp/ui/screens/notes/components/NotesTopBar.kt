package com.example.simplenotesapp.ui.screens.notes.components

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
import com.example.simplenotesapp.domain.util.NotesOrder
import com.example.simplenotesapp.ui.theme.MainTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesTopBar(
    onSort: () -> Unit,
    onSettings: () -> Unit,
    notesOrder: NotesOrder = NotesOrder.Timestamp
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.label_your_note),
                style = MainTheme.typography.header
            )
        },
        actions = {
            IconButton(
                onClick = (onSort)
            ) {
                Icon(
                    painter = painterResource(id = if (notesOrder == NotesOrder.Timestamp)
                        R.drawable.order_by_timestamp
                    else
                        R.drawable.order_by_title
                    ),
                    contentDescription = stringResource(id = R.string.label_order)
                )
            }
            IconButton(
                onClick = (onSettings)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.settings),
                    contentDescription = stringResource(id = R.string.btn_settings)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MainTheme.colors.primaryBackground,
            actionIconContentColor = MainTheme.colors.invertColor,
            titleContentColor = MainTheme.colors.primaryTextColor
        )
    )
}