package com.example.notes.domain.util

import androidx.compose.runtime.mutableStateOf
import com.example.notes.R

sealed class NotesOrder(val name: Int) {

    object Title : NotesOrder(name = R.string.title)

    object Timestamp : NotesOrder(name = R.string.timestamp)

//    companion object{
//        val notesOrderMap = mapOf(
//            Title to Title.name,
//            Timestamp to Timestamp.name
//        )
//        val currentNotesOrder = mutableStateOf<NotesOrder>(Title)
//    }

}