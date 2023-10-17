package com.example.jetnotesapp.ui.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetnotesapp.domain.models.Note
import com.example.jetnotesapp.domain.models.SettingsBundle
import com.example.jetnotesapp.domain.usecase.UseCases
import com.example.jetnotesapp.domain.util.NotesOrder
import com.example.jetnotesapp.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
 * State for the Notes screen
 */
data class NotesState(
    var notes: List<Note> = emptyList(),
    var orderType: OrderType = OrderType.Descending,
    var notesOrder: NotesOrder = NotesOrder.Timestamp,
    var query: String = ""
)

/*
 * ViewModel for the Notes screen and Settings screen
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _notesState = mutableStateOf(NotesState())
    val notesState: State<NotesState> = _notesState

    // Set NotesOrder and OrderType
    init {
        getNotes(
            notesOrder = getSettings().notesOrder,
            orderType = getSettings().orderType
        )
    }

    fun onEvent(event: MainEvent) {
        when (event) {

            is MainEvent.ChangeNotesOrder -> {
                _notesState.value.notesOrder = event.notesOrder
                saveSettings(
                    settingsBundle = useCases.getSettings.invoke()
                    .copy(notesOrder = event.notesOrder)
                )
            }

            is MainEvent.ChangeOrderType -> {
                _notesState.value.orderType = when(_notesState.value.orderType){
                    OrderType.Descending -> OrderType.Ascending
                    OrderType.Ascending -> OrderType.Descending
                }
                saveSettings(
                    settingsBundle = useCases.getSettings.invoke()
                        .copy(orderType = _notesState.value.orderType)
                )
            }

            is MainEvent.Delete -> {
                viewModelScope.launch {
                    useCases.insertNote(event.note.copy(deleted = true))
                }
            }

            is MainEvent.EnteredSearch -> {
                if (event.value.isEmpty()){
                    _notesState.value = _notesState.value.copy(
                        query = ""
                    )
                }else {
                    _notesState.value = _notesState.value.copy(
                        query = event.value
                    )
                }
                getNotes(
                    notesOrder = _notesState.value.notesOrder,
                    orderType = _notesState.value.orderType
                )
            }

        }
    }


    private fun getNotes(notesOrder: NotesOrder, orderType: OrderType) {
        useCases.getNotes(
            query = _notesState.value.query,
            deleted = false,
            notesOrder = notesOrder,
            orderType = orderType
        ).onEach { notes ->
            _notesState.value = notesState.value.copy(
                notes = notes,
                notesOrder = notesOrder,
                orderType = orderType
            )
        }.launchIn(viewModelScope)
    }

    fun getSettings(): SettingsBundle {
        return useCases.getSettings.invoke()
    }

    fun saveSettings(settingsBundle: SettingsBundle) {
        useCases.saveSettings.invoke(settingsBundle = settingsBundle)
        getNotes(
            notesOrder = _notesState.value.notesOrder,
            orderType = _notesState.value.orderType
        )
    }
}