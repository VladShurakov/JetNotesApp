package com.vladshurakov.jetnotesapp.feature_notes.presenter.viewmodel.events

import androidx.compose.ui.focus.FocusState

sealed interface EditEvent {
    data class EnteredTitle(val value: String) : EditEvent
    data class ChangeTitleFocus(val focusState: FocusState) : EditEvent
    data class EnteredContent(val value: String) : EditEvent
    data class ChangeContentFocus(val focusState: FocusState) : EditEvent
    data object Insert : EditEvent
}