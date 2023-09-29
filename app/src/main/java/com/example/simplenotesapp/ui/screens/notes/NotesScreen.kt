package com.example.simplenotesapp.ui.screens.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.simplenotesapp.R
import com.example.simplenotesapp.ui.screens.MainViewModel
import com.example.simplenotesapp.ui.screens.notes.components.NoteView
import com.example.simplenotesapp.ui.screens.notes.components.NotesTopBar
import com.example.simplenotesapp.ui.theme.MainTheme
import com.example.simplenotesapp.ui.util.Screen

@Composable
fun NotesScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Scaffold(
        topBar = {
            NotesTopBar(
                onSort = {
                    viewModel.onEvent(MainEvent.ChangeNotesOrderType)
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
                        contentDescription = stringResource(id = R.string.btn_add_note),
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
            if (state.notes.isEmpty()) {
                item {
                        Text(
                            text = stringResource(id = R.string.label_no_note),
                            style = MainTheme.typography.title,
                            color = MainTheme.colors.primaryTextColor,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = stringResource(id = R.string.label_record_note),
                            style = MainTheme.typography.body,
                            color = MainTheme.colors.secondaryTextColor,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
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
                        viewModel.onEvent(MainEvent.ToTrash(note = note))
                    }
                )
            }
        }
    }
}
