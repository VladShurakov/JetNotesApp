package com.example.simplenotesapp.domain.util

sealed interface OrderType {

    object Ascending : OrderType

    object Descending : OrderType
}