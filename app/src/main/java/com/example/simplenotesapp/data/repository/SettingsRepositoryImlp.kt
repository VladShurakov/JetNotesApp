package com.example.simplenotesapp.data.repository

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.runtime.mutableStateOf
import com.example.simplenotesapp.domain.models.SettingsBundle
import com.example.simplenotesapp.domain.repository.SettingsRepository
import com.example.simplenotesapp.domain.util.NotesOrder
import com.example.simplenotesapp.domain.util.OrderType
import com.example.simplenotesapp.domain.util.SimpleNotesCorners
import com.example.simplenotesapp.domain.util.SimpleNotesSize
import com.example.simplenotesapp.domain.util.SimpleNotesStyle

class SettingsRepositoryImlp(context: Context): SettingsRepository {

    companion object Constants {
        private const val PREFS_NAME = "SimpleNotesApp"
        private const val IS_DARK_MODE_KEY = "isDarkMode"
        private const val TEXT_SIZE_KEY = "textSize"
        private const val CORNET_STYLE_KEY = "cornerStyle"
        private const val STYLE_KEY = "style"
        private const val NOTES_ORDER_KEY = "notesOrder"
        private const val ORDER_TYPE_KEY = "orderType"
    }

    private var defaultSettingsBundle = mutableStateOf(
        SettingsBundle(
            isDarkMode = false,
            textSize = SimpleNotesSize.Normal,
            cornerStyle = SimpleNotesCorners.Rounded,
            style = SimpleNotesStyle.Yellow,
            notesOrder = NotesOrder.Timestamp,
            orderType = OrderType.Descending
        )
    )

    private val sharedPreferences = context.getSharedPreferences(
        PREFS_NAME,
        ComponentActivity.MODE_PRIVATE
    )

    override fun getSettings(): SettingsBundle {
        defaultSettingsBundle.value = SettingsBundle(
            isDarkMode = sharedPreferences.getBoolean(
                IS_DARK_MODE_KEY,
                defaultSettingsBundle.value.isDarkMode
            ),
            textSize = SimpleNotesSize.valueOf(
                sharedPreferences.getString(
                    TEXT_SIZE_KEY,
                    defaultSettingsBundle.value.textSize.name
                ) ?: defaultSettingsBundle.value.textSize.name
            ),
            cornerStyle = SimpleNotesCorners.valueOf(
                sharedPreferences.getString(
                    CORNET_STYLE_KEY,
                    defaultSettingsBundle.value.cornerStyle.name
                ) ?: defaultSettingsBundle.value.cornerStyle.name
            ),
            style = SimpleNotesStyle.valueOf(
                sharedPreferences.getString(
                    STYLE_KEY,
                    defaultSettingsBundle.value.style.name
                ) ?: defaultSettingsBundle.value.style.name
            ),
            notesOrder = NotesOrder.valueOf(
                sharedPreferences.getString(
                    NOTES_ORDER_KEY,
                    defaultSettingsBundle.value.notesOrder.name
                )  ?: defaultSettingsBundle.value.notesOrder.name
            ),
            orderType = OrderType.valueOf(
                sharedPreferences.getString(
                    ORDER_TYPE_KEY,
                    defaultSettingsBundle.value.orderType.name
                )  ?: defaultSettingsBundle.value.orderType.name
            )
        )
        return defaultSettingsBundle.value
    }

    override fun saveSettings(settingsBundle: SettingsBundle) {
        defaultSettingsBundle.value = settingsBundle
        sharedPreferences.edit().apply {
            putBoolean(IS_DARK_MODE_KEY, defaultSettingsBundle.value.isDarkMode)
            putString(TEXT_SIZE_KEY, defaultSettingsBundle.value.textSize.name)
            putString(CORNET_STYLE_KEY, defaultSettingsBundle.value.cornerStyle.name)
            putString(STYLE_KEY, defaultSettingsBundle.value.style.name)
            putString(NOTES_ORDER_KEY, defaultSettingsBundle.value.notesOrder.name)
            putString(ORDER_TYPE_KEY, defaultSettingsBundle.value.orderType.name)
        }.apply()
    }

}