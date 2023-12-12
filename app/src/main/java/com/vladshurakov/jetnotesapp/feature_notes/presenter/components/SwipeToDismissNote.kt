package com.vladshurakov.jetnotesapp.feature_notes.presenter.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vladshurakov.jetnotesapp.R
import com.vladshurakov.jetnotesapp.feature_notes.domain.models.NoteEntity
import com.vladshurakov.jetnotesapp.theme.MainTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeToDismissNote(
    note: NoteEntity, onClick: () -> Unit = {}, onPin: (() -> Unit)? = null,
    dismissState: DismissState, @DrawableRes starDrawable: Int,
) {
    SwipeToDismiss(
        state = dismissState,
        background = {
            val color = when (dismissState.dismissDirection) {
                null -> Color.Transparent

                else -> MainTheme.colors.tintColor
            }

            Box {
                NoteView(color = color)
                if (dismissState.dismissDirection != null){
                    Icon(
                        painter = painterResource(
                            id = when (dismissState.dismissDirection){
                                DismissDirection.StartToEnd -> starDrawable
                                else -> R.drawable.ic_delete
                            }
                        ),
                        contentDescription = null,
                        tint = MainTheme.colors.primaryBackground,
                        modifier = Modifier
                            .let {
                                if (dismissState.dismissDirection == DismissDirection.StartToEnd){
                                    it.align(Alignment.CenterStart)
                                } else{
                                    it.align(Alignment.CenterEnd)
                                }
                            }
                            .padding(horizontal = 24.dp)
                            .size(42.dp)
                    )
                }
            }
        },
        dismissContent = {
            NoteView(
                note = note,
                onClick = (onClick),
                onPin = (onPin)
            )
        }
    )
}