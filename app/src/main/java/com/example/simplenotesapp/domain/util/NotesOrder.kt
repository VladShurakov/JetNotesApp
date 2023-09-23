package com.example.simplenotesapp.domain.util

import com.example.simplenotesapp.R

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