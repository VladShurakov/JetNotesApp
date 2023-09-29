package com.example.simplenotesapp.domain.util

import com.example.simplenotesapp.R

enum class NotesOrder(val stringName: Int) {
    Title(stringName = R.string.notes_order_title),
    Timestamp(stringName = R.string.notes_order_timestamp)
}