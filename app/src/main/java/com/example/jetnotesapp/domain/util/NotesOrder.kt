package com.example.jetnotesapp.domain.util

import com.example.jetnotesapp.R

enum class NotesOrder(val stringName: Int) {
    Timestamp(stringName = R.string.notes_order_timestamp),
    Title(stringName = R.string.notes_order_title)
}