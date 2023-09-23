package com.example.notes.ui.screens.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notes.R
import com.example.notes.ui.screens.notes.components.NoteView
import com.example.notes.ui.screens.notes.components.NotesTopBar
import com.example.notes.ui.theme.MainTheme
import com.example.notes.ui.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
    navController: NavController,
    viewModel: NotesViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Scaffold(
        topBar = {
            NotesTopBar(
                onSort = {
                    viewModel.onEvent(NotesEvent.ChangeOrderType)
                },
                onSettings = {
                    navController.navigate(Screen.Settings.route)
                },
                viewModel.state.value.notesOrder
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
                        contentDescription = stringResource(id = R.string.add_note),
                        tint = MainTheme.colors.primaryBackground
                    )
                },
                modifier = Modifier
                    .padding(bottom = 32.dp, end = 12.dp)
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MainTheme.colors.primaryBackground)
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
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
                        viewModel.onEvent(NotesEvent.ToTrash(note = note))
                    }
                )
            }
        }
    }
}
