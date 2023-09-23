package com.example.simplenotesapp.ui.screens.add_edit_note

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.simplenotesapp.R
import com.example.simplenotesapp.ui.screens.add_edit_note.components.AddEditNoteTopAppBar
import com.example.simplenotesapp.ui.screens.add_edit_note.components.TransparentHintTextField
import com.example.simplenotesapp.ui.theme.MainTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AddEditNoteScreen(
    navController: NavController,
    viewModel: AddEditNoteViewModel = hiltViewModel(),
) {

    val titleState = viewModel.noteTitle.value
    val timestamp = viewModel.timestamp.longValue
    val contentState = viewModel.noteContent.value
    val scrollState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            AddEditNoteTopAppBar(
                onBack = {
                    viewModel.onEvent(AddEditNoteEvent.InsertNote)
                    navController.navigateUp()
                },
                onSave = {
                    viewModel.onEvent(AddEditNoteEvent.InsertNote)
                    keyboardController?.hide()
                }
            )
        }
    ) { topBarPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MainTheme.colors.primaryBackground)
                .padding(topBarPadding)
                .padding(horizontal = 16.dp)
                .navigationBarsPadding()
                .imePadding()
                .verticalScroll(state = scrollState)
        ) {

            TransparentHintTextField(
                text = titleState.text,
                hint = stringResource(id = R.string.title),
                onValueChange = {
                    viewModel.onEvent(AddEditNoteEvent.EnteredTitle(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditNoteEvent.ChangeTitleFocus(it))
                },
                isHintVisible = titleState.isHintVisible,
                textStyle = MainTheme.typography.title.copy(
                    color = if (titleState.isHintVisible) {
                        MainTheme.colors.secondaryTextColor
                    } else {
                        MainTheme.colors.primaryTextColor
                    }
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = SimpleDateFormat(
                    stringResource(id = R.string.date_format_pattern),
                    Locale.getDefault()
                ).format(Date(timestamp)),
                style = MainTheme.typography.caption
            )

            Spacer(modifier = Modifier.height(16.dp))

            TransparentHintTextField(
                text = contentState.text,
                hint = stringResource(id = R.string.start_typing),
                onValueChange = {
                    viewModel.onEvent(AddEditNoteEvent.EnteredContent(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditNoteEvent.ChangeContentFocus(it))
                },
                isHintVisible = contentState.isHintVisible,
                textStyle = MainTheme.typography.body.copy(
                    color = if (contentState.isHintVisible) {
                        MainTheme.colors.secondaryTextColor
                    } else {
                        MainTheme.colors.primaryTextColor
                    }
                )
            )
        }
    }
}