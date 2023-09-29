package com.example.simplenotesapp.ui.screens.settings.components

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
fun SettingsTopBar(
    onBack: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.label_settings),
                style = MainTheme.typography.header
            )
        },
        navigationIcon = {
            IconButton(
                onClick = (onBack)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_left),
                    contentDescription = stringResource(id = R.string.btn_back),
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MainTheme.colors.primaryBackground,
            titleContentColor = MainTheme.colors.primaryTextColor,
            navigationIconContentColor = MainTheme.colors.invertColor
        )
    )
}