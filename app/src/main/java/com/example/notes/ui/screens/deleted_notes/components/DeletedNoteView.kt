package com.example.notes.ui.screens.deleted_notes.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.notes.R
import com.example.notes.ui.theme.MainTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun DeletedNoteView(
    title: String, content: String, timestamp: Long,
    onClick: () -> Unit, onDelete: () -> Unit, onRecover: () -> Unit
) {

    Card(
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        colors = CardDefaults.cardColors(MainTheme.colors.secondaryBackground),
        shape = MainTheme.shapes.cornersStyle,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {

        Box(
            modifier = Modifier
                .clickable(onClick = onClick)
        ) {

            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.matchParentSize()
            ) {

                IconButton(
                    onClick = (onRecover),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.recover),
                        contentDescription = stringResource(id = R.string.recover),
                        tint = MainTheme.colors.invertColor
                    )
                }

                IconButton(
                    onClick = (onDelete),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.delete),
                        contentDescription = stringResource(id = R.string.delete),
                        tint = MainTheme.colors.invertColor
                    )
                }

            }

            Column {

                Text(
                    text = title,
                    maxLines = 1,
                    style = MainTheme.typography.title,
                    color = MainTheme.colors.primaryTextColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, start = 12.dp, end = 12.dp)
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = if (isContentBlank(content = content))
                        stringResource(id = R.string.no_content)
                    else
                        content,
                    maxLines = 1,
                    style = MainTheme.typography.body,
                    color = MainTheme.colors.secondaryTextColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp, end = 12.dp)
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = SimpleDateFormat(
                        "dd MMMM, yyyy HH:mm ZZZZ",
                        Locale.getDefault(),
                    ).format(Date(timestamp)),
                    style = MainTheme.typography.caption,
                    color = MainTheme.colors.secondaryTextColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp, start = 12.dp, end = 12.dp)
                )

            }

        }

    }

}

private fun isContentBlank(content: String): Boolean{
    return content.isBlank() || content.isEmpty() ||
            content.startsWith('\n') || content.startsWith(' ')
}