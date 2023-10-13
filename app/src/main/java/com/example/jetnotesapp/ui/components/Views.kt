package com.example.jetnotesapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.jetnotesapp.R
import com.example.jetnotesapp.ui.theme.MainTheme
import org.ocpsoft.prettytime.PrettyTime
import java.util.Date

@Composable
fun NoteView(
    title: String, content: String, timestamp: Long,
    onClick: () -> Unit, onDelete: () -> Unit, onRecover: (() -> Unit)? = null
) {
    fun isContentBlank(content: String): Boolean = content.isBlank() || content.isEmpty()

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
                if (onRecover != null){
                    IconButton(
                        onClick = (onRecover),
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.recover),
                            contentDescription = stringResource(id = R.string.btn_recover),
                            tint = MainTheme.colors.invertColor
                        )
                    }
                }

                IconButton(
                    onClick = (onDelete),
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
}

@Composable
fun SettingsView(
    onClick: () -> Unit,
    title: String,
    selectionText: String,
    selectionTextColor: Color = MainTheme.colors.secondaryTextColor
) {
    Button(
        onClick = (onClick),
        colors = ButtonDefaults.textButtonColors(),
        shape = RectangleShape,
        contentPadding = PaddingValues(start = 16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 64.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            Text(
                text = title,
                style = MainTheme.typography.body,
                color = MainTheme.colors.primaryTextColor
            )

            Text(
                text = selectionText,
                style = MainTheme.typography.body,
                color = selectionTextColor
            )

        }
    }
}