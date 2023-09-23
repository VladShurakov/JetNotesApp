package com.example.notes.domain.util

sealed interface OrderType {

    object Ascending : OrderType

    object Descending : OrderType
}