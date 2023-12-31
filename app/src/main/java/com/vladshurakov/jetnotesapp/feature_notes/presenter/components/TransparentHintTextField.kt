package com.vladshurakov.jetnotesapp.feature_notes.presenter.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import com.vladshurakov.jetnotesapp.theme.MainTheme

@Composable
fun TransparentHintTextField(
    text: String,
    hint: String,
    isHintVisible: Boolean = true,
    onValueChange: (String) -> Unit,
    onFocusChange: (FocusState) -> Unit,
    textStyle: TextStyle = TextStyle(),
    singleLine: Boolean = false
) {
    Box {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            textStyle = textStyle,
            singleLine = singleLine,
            cursorBrush = SolidColor(MainTheme.colors.invertColor),
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    onFocusChange(it)
                }
        )
        if (isHintVisible) {
            Text(
                text = hint,
                style = textStyle,
                color = MainTheme.colors.secondaryTextColor,
                fontSize = MainTheme.typography.body.fontSize
            )
        }
    }
}