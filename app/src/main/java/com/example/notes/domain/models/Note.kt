package com.example.notes.domain.models

data class Note(

    var title: String,

    val content: String,

    val timestamp: Long,

    val inTrash: Boolean = false,

    val id: Long? = null,
    )