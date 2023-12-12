package com.vladshurakov.jetnotesapp.feature_notes.domain.usecase

import com.vladshurakov.jetnotesapp.feature_notes.domain.models.Folder
import com.vladshurakov.jetnotesapp.feature_notes.domain.models.NoteEntity
import com.vladshurakov.jetnotesapp.feature_notes.domain.repository.NotesRepository
import com.vladshurakov.jetnotesapp.util.OrderType
import kotlinx.coroutines.flow.Flow

class GetNotes(private val repository: NotesRepository) {
    operator fun invoke(orderType: OrderType, folder: Folder): Flow<List<NoteEntity>> {
        return when (orderType) {
            OrderType.Ascending -> {
                repository.getAsc(folder)
            }

            OrderType.Descending -> {
                repository.getDesc(folder)
            }
        }
    }
}