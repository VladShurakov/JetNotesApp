package com.vladshurakov.jetnotesapp.feature_notes.domain.usecase

import com.vladshurakov.jetnotesapp.feature_notes.domain.models.NoteEntity
import com.vladshurakov.jetnotesapp.feature_notes.domain.repository.NotesRepository
import com.vladshurakov.jetnotesapp.util.OrderType
import kotlinx.coroutines.flow.Flow

class SearchNotes(private val repository: NotesRepository){

    operator fun invoke(query: String, orderType: OrderType): Flow<List<NoteEntity>> {
        return when (orderType){
            OrderType.Ascending -> {
                repository.getAsc(query)
            }

            OrderType.Descending -> {
                repository.getDesc(query)
            }
        }
    }
}