package com.example.jetnotesapp.ui.screens.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetnotesapp.R
import com.example.jetnotesapp.ui.components.NoteView
import com.example.jetnotesapp.ui.components.NotesTopBar
import com.example.jetnotesapp.ui.screens.MainEvent
import com.example.jetnotesapp.ui.screens.MainViewModel
import com.example.jetnotesapp.ui.theme.MainTheme
import com.example.jetnotesapp.ui.util.Screen

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NotesScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {

    val state = viewModel.notesState.value
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            NotesTopBar(
                onSort = {
                    viewModel.onEvent(MainEvent.ChangeOrderType)
                },
                onSettings = {
                    navController.navigate(Screen.Settings.route)
                },
                notesOrder = viewModel.notesState.value.notesOrder
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                containerColor = MainTheme.colors.tintColor,
                onClick = {
                    navController.navigate(Screen.AddEditNote.route)
                },
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.add),
                        contentDescription = stringResource(id = R.string.btn_add_note),
                        tint = MainTheme.colors.primaryBackground
                    )
                }
            )
        }
    ) { topBarPadding ->
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(MainTheme.colors.primaryBackground)
                .padding(topBarPadding)
                .navigationBarsPadding()
                .imePadding()
        ) {
            item {
                // Search TextField
                TextField(
                    value = viewModel.notesState.value.query,
                    onValueChange = {
                        viewModel.onEvent(MainEvent.EnteredSearch(it))
                    },
                    textStyle = MainTheme.typography.title,
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.search),
                            color = MainTheme.colors.secondaryTextColor,
                            style = MainTheme.typography.title
                        )
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = stringResource(id = R.string.search),
                            tint = MainTheme.colors.invertColor
                        )
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                keyboardController?.hide()
                                viewModel.onEvent(MainEvent.EnteredSearch(""))
                                focusManager.clearFocus()
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.close),
                                contentDescription = stringResource(id = R.string.search)
                            )
                        }
                    },
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                            focusManager.clearFocus()
                        }
                    ),
                    singleLine = true,
                    shape = MainTheme.shapes.cornersStyle,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MainTheme.colors.secondaryBackground,
                        unfocusedContainerColor = MainTheme.colors.secondaryBackground,
                        focusedIndicatorColor = Color.Unspecified,
                        unfocusedIndicatorColor = Color.Unspecified,
                        cursorColor = MainTheme.colors.invertColor,
                        focusedTrailingIconColor = MainTheme.colors.invertColor,
                        unfocusedTrailingIconColor = MainTheme.colors.secondaryBackground,
                        focusedTextColor = MainTheme.colors.primaryTextColor,
                        unfocusedTextColor = MainTheme.colors.primaryTextColor
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            items(state.notes) { note ->
                NoteView(
                    title = note.title,
                    content = note.content,
                    timestamp = note.timestamp,
                    onClick = {
                        navController.navigate(
                            Screen.AddEditNote.route + "?id=${note.id}"
                        )
                    },
                    onDelete = {
                        viewModel.onEvent(MainEvent.Delete(note = note))
                    }
                )
            }
        }
    }
}
