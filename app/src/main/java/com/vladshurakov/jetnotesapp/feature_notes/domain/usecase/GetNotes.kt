package com.vladshurakov.jetnotesapp.feature_notes.domain.usecase

import com.vladshurakov.jetnotesapp.feature_notes.domain.models.Note
import com.vladshurakov.jetnotesapp.feature_notes.domain.repository.NotesRepository
import com.vladshurakov.jetnotesapp.util.OrderType
import kotlinx.coroutines.flow.Flow

class GetNotes(private val repository: NotesRepository) {
    operator fun invoke(orderType: OrderType): Flow<List<Note>> {
        return when (orderType) {
            OrderType.Ascending -> {
                repository.getAsc()
            }

            OrderType.Descending -> {
                repository.getDesc()
            }
        }
    }
}