package com.vladshurakov.jetnotesapp.domain.models

data class Note(
    var title: String,
    val content: String,
    val timestamp: Long,
    val deleted: Boolean = false,
    val id: Long? = null,
)