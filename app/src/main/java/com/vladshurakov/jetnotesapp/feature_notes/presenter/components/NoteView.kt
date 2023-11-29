package com.vladshurakov.jetnotesapp.feature_notes.presenter.components

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.vladshurakov.jetnotesapp.R
import com.vladshurakov.jetnotesapp.theme.MainTheme
import org.ocpsoft.prettytime.PrettyTime
import java.util.Date

@Composable
fun NoteView(
    title: String, content: String, timestamp: Long,
    onClick: () -> Unit, onRecover: (() -> Unit)? = null,
    onArchive: (() -> Unit)? = null, onUnarchive: (() -> Unit)? = null,
    onDelete: () -> Unit
) {
    fun isContentBlank(content: String): Boolean = content.isBlank() || content.isEmpty()

    Button(
        onClick = (onClick),
        colors = ButtonDefaults.buttonColors(
            containerColor = MainTheme.colors.secondaryBackground
        ),
        shape = MainTheme.shapes.cornersStyle,
        contentPadding = PaddingValues(start = 12.dp, top = 12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        Row {
            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
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
                    text = PrettyTime().format(Date(timestamp)),
                    style = MainTheme.typography.caption,
                    color = MainTheme.colors.secondaryTextColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                )
            }

            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.align(Alignment.Bottom)
            ) {
                if (onRecover != null) {
                    IconButton(
                        onClick = (onRecover),
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_restore),
                            contentDescription = stringResource(id = R.string.btn_recover),
                            tint = MainTheme.colors.invertColor
                        )
                    }
                }

                if (onArchive != null) {
                    IconButton(
                        onClick = (onArchive),
                    ) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.ic_archive
                            ),
                            contentDescription = stringResource(id = R.string.archived_notes),
                            tint = MainTheme.colors.invertColor
                        )
                    }
                }

                if (onUnarchive != null) {
                    IconButton(
                        onClick = (onUnarchive),
                    ) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.ic_unarchive
                            ),
                            contentDescription = stringResource(id = R.string.archived_notes),
                            tint = MainTheme.colors.invertColor
                        )
                    }
                }

                IconButton(
                    onClick = (onDelete),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete),
                        contentDescription = stringResource(id = R.string.btn_delete),
                        tint = MainTheme.colors.invertColor
                    )
                }

            }
        }
    }
}