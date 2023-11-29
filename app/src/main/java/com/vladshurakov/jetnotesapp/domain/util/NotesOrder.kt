package com.vladshurakov.jetnotesapp.domain.util

import com.vladshurakov.jetnotesapp.R

enum class NotesOrder(val stringName: Int) {
    Timestamp(stringName = R.string.notes_order_timestamp),
    Title(stringName = R.string.notes_order_title)
}