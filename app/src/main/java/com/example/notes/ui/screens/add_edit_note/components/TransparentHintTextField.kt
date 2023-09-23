package com.example.notes.ui.screens.add_edit_note.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextStyle
import com.example.notes.ui.theme.MainTheme

@Composable
fun TransparentHintTextField(
    text: String,
    hint: String,
    isHintVisible: Boolean = true,
    onValueChange: (String) -> Unit,
    onFocusChange: (FocusState) -> Unit,
    textStyle: TextStyle = TextStyle(),
) {

    Box() {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            textStyle = textStyle,
            modifier = Modifier
                .fillMaxSize()
                .onFocusChanged {
                    onFocusChange(it)
                }
        )
        if (isHintVisible) {
            Text(
                text = hint,
                style = textStyle,
                color = MainTheme.colors.secondaryTextColor,
            )
        }
    }
}