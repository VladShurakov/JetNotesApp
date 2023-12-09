package com.vladshurakov.jetnotesapp.feature_notes.presenter.components

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.vladshurakov.jetnotesapp.R
import com.vladshurakov.jetnotesapp.feature_notes.domain.models.Note
import com.vladshurakov.jetnotesapp.theme.MainTheme
import org.ocpsoft.prettytime.PrettyTime
import java.util.Date

@Composable
fun NoteView(
    note: Note = Note(title = "ㅤ", content = "ㅤ", timestamp = 0), onClick: () -> Unit = {},
    onPin: (() -> Unit)? = null, color: Color = MainTheme.colors.secondaryBackground
) {
    fun isContentBlank(content: String): Boolean = content.isBlank() || content.isEmpty()

    Button(
        onClick = (onClick),
        colors = ButtonDefaults.buttonColors(
            containerColor = color
        ),
        shape = MainTheme.shapes.cornersStyle,
        contentPadding = PaddingValues(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = note.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MainTheme.typography.title,
                    color = MainTheme.colors.primaryTextColor,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = if (isContentBlank(content = note.content))
                        stringResource(id = R.string.hint_no_content)
                    else
                        note.content,
                    maxLines = 1,
                    style = MainTheme.typography.body,
                    color = MainTheme.colors.secondaryTextColor
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = if (note.timestamp == 0.toLong()) "   " else PrettyTime().format(Date(note.timestamp)),
                    style = MainTheme.typography.caption,
                    color = MainTheme.colors.secondaryTextColor
                )
            }

            if (onPin != null){
                IconButton(
                    onClick = (onPin)
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (note.pinned) R.drawable.ic_pinned else R.drawable.ic_unpinned
                        ),
                        contentDescription = stringResource(
                            id = if (note.pinned) R.string.pinned else R.string.unpinned
                        ),
                        tint = MainTheme.colors.invertColor
                    )
                }
            }
        }
    }
}