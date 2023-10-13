package com.example.jetnotesapp.data.repository


import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.runtime.mutableStateOf
import com.example.jetnotesapp.domain.models.SettingsBundle
import com.example.jetnotesapp.domain.repository.SettingsRepository
import com.example.jetnotesapp.domain.util.JetNotesCorners
import com.example.jetnotesapp.domain.util.JetNotesSize
import com.example.jetnotesapp.domain.util.JetNotesStyle
import com.example.jetnotesapp.domain.util.JetNotesTheme
import com.example.jetnotesapp.domain.util.NotesOrder
import com.example.jetnotesapp.domain.util.OrderType

private const val SHARED_PREFS_NAME = "jet_notes_shared_prefs"
private const val THEME_KEY = "theme"
private const val TEXT_SIZE_KEY = "text_size"
private const val CORNET_STYLE_KEY = "corner_style"
private const val STYLE_KEY = "style"
private const val NOTES_ORDER_KEY = "notes_order"
private const val ORDER_TYPE_KEY = "order_type"

class SettingsRepositoryImlp(context: Context): SettingsRepository {

    private val sharedPreferences = context.getSharedPreferences(
        SHARED_PREFS_NAME,
        ComponentActivity.MODE_PRIVATE
    )
    private var defaultSettingsBundle = mutableStateOf(
        SettingsBundle(
            theme = JetNotesTheme.Default,
            textSize = JetNotesSize.Medium,
            cornerStyle = JetNotesCorners.Rounded,
            style = JetNotesStyle.Yellow,
            notesOrder = NotesOrder.Timestamp,
            orderType = OrderType.Descending
        )
    )

    override fun getSettings(): SettingsBundle {
        defaultSettingsBundle.value = SettingsBundle(
            theme = JetNotesTheme.valueOf(
                sharedPreferences.getString(
                    THEME_KEY,
                    defaultSettingsBundle.value.theme.name
                ) ?: defaultSettingsBundle.value.theme.name
            ),
            textSize = JetNotesSize.valueOf(
                sharedPreferences.getString(
                    TEXT_SIZE_KEY,
                    defaultSettingsBundle.value.textSize.name
                ) ?: defaultSettingsBundle.value.textSize.name
            ),
            cornerStyle = JetNotesCorners.valueOf(
                sharedPreferences.getString(
                    CORNET_STYLE_KEY,
                    defaultSettingsBundle.value.cornerStyle.name
                ) ?: defaultSettingsBundle.value.cornerStyle.name
            ),
            style = JetNotesStyle.valueOf(
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
            putString(THEME_KEY, defaultSettingsBundle.value.theme.name)
            putString(TEXT_SIZE_KEY, defaultSettingsBundle.value.textSize.name)
            putString(CORNET_STYLE_KEY, defaultSettingsBundle.value.cornerStyle.name)
            putString(STYLE_KEY, defaultSettingsBundle.value.style.name)
            putString(NOTES_ORDER_KEY, defaultSettingsBundle.value.notesOrder.name)
            putString(ORDER_TYPE_KEY, defaultSettingsBundle.value.orderType.name)
        }.apply()
    }

}