package com.vladshurakov.jetnotesapp.feature_settings.presenter.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.vladshurakov.jetnotesapp.R
import com.vladshurakov.jetnotesapp.theme.MainTheme
import com.vladshurakov.jetnotesapp.util.OrderType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title: String,
    navigationIcon: @Composable () -> Unit = { },
    actions: @Composable RowScope.() -> Unit = { }
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MainTheme.typography.header
            )
        },
        navigationIcon = (navigationIcon),
        actions = (actions),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MainTheme.colors.primaryBackground,
            navigationIconContentColor = MainTheme.colors.invertColor,
            actionIconContentColor = MainTheme.colors.invertColor,
            titleContentColor = MainTheme.colors.primaryTextColor
        )
    )
}

@Composable
fun NotesTopBar(
    orderType: OrderType,
    onSort: () -> Unit,
    onSettings: () -> Unit,
) {
    CustomTopAppBar(
        title = stringResource(id = R.string.app_name),
        actions = {
            IconButton(
                onClick = (onSort)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_order),
                    contentDescription = stringResource(id = R.string.label_order),
                    tint = MainTheme.colors.invertColor,
                    modifier = Modifier
                        .graphicsLayer(
                            scaleY = if (orderType == OrderType.Descending) 1f else -1f
                        )
                )
            }
            IconButton(
                onClick = (onSettings)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_settings),
                    contentDescription = stringResource(id = R.string.btn_settings),
                    tint = MainTheme.colors.invertColor
                )
            }
        }
    )
}

@Composable
fun SettingsTopBar(
    onBack: () -> Unit,
) {
    CustomTopAppBar(
        title = stringResource(id = R.string.label_settings),
        navigationIcon = {
            IconButton(
                onClick = (onBack)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = stringResource(id = R.string.btn_back),
                )
            }
        }
    )
}

@Composable
fun DeletedNotesTopBar(
    onBack: () -> Unit,
    onDeleteAll: () -> Unit
) {
    CustomTopAppBar(
        title = stringResource(id = R.string.label_deleted_notes),
        navigationIcon = {
            IconButton(
                onClick = (onBack)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = stringResource(id = R.string.btn_back),
                )
            }
        },
        actions = {
            IconButton(
                onClick = (onDeleteAll)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_delete_all),
                    contentDescription = stringResource(id = R.string.msg_delete_all_notes),
                )
            }
        }
    )
}

@Composable
fun ArchivedNotesTopBar(
    onBack: () -> Unit
) {
    CustomTopAppBar(
        title = stringResource(id = R.string.archived_notes),
        navigationIcon = {
            IconButton(
                onClick = (onBack)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = stringResource(id = R.string.btn_back),
                )
            }
        }
    )
}

@Composable
fun AddEditNoteTopBar(
    onBack: () -> Unit,
    onSave: () -> Unit
) {
    CustomTopAppBar(
        title = "",
        navigationIcon = {
            IconButton(
                onClick = (onBack)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = stringResource(id = R.string.btn_back),
                )
            }
        },
        actions = {
            IconButton(
                onClick = (onSave)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_save),
                    contentDescription = stringResource(id = R.string.btn_save_note),
                )
            }
        }
    )
}