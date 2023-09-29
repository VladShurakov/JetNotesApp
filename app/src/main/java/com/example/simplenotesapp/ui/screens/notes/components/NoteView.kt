package com.example.simplenotesapp.ui.screens.notes.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.simplenotesapp.R
import com.example.simplenotesapp.ui.theme.MainTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun NoteView(
    title: String, content: String, timestamp: Long,
    onClick: () -> Unit, onDelete: () -> Unit
) {

    Button(
        onClick = (onClick),
        colors = ButtonDefaults.buttonColors(
            containerColor = MainTheme.colors.secondaryBackground
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
        shape = MainTheme.shapes.cornersStyle,
        contentPadding = PaddingValues(start = 12.dp, top = 12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {

        Row{

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = title,
                    maxLines = 1,
                    style = MainTheme.typography.title,
                    color = MainTheme.colors.primaryTextColor,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = if (isContentBlank(content = content))
                        stringResource(id = R.string.hint_no_content)
                    else
                        content,
                    maxLines = 1,
                    style = MainTheme.typography.body,
                    color = MainTheme.colors.secondaryTextColor,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = SimpleDateFormat(
                        stringResource(id = R.string.date_format_pattern),
                        Locale.getDefault(),
                    ).format(Date(timestamp)),
                    style = MainTheme.typography.caption,
                    color = MainTheme.colors.secondaryTextColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                )

            }

            IconButton(
                onClick = (onDelete),
                modifier = Modifier.align(Alignment.Bottom)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = stringResource(id = R.string.btn_delete),
                    tint = MainTheme.colors.invertColor
                )
            }

        }
    }
}

private fun isContentBlank(content: String): Boolean{
    return content.isBlank() || content.isEmpty() ||
            content.startsWith('\n') || content.startsWith(' ')
}