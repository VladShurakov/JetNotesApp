package com.vladshurakov.jetnotesapp.feature_settings.data.repository

import android.content.Context
import androidx.activity.ComponentActivity
import com.vladshurakov.jetnotesapp.feature_settings.domain.models.SettingsBundle
import com.vladshurakov.jetnotesapp.feature_settings.domain.repository.SettingsRepository
import com.vladshurakov.jetnotesapp.util.JetNotesCorners
import com.vladshurakov.jetnotesapp.util.JetNotesSize
import com.vladshurakov.jetnotesapp.util.JetNotesStyle
import com.vladshurakov.jetnotesapp.util.JetNotesTheme
import com.vladshurakov.jetnotesapp.util.OrderType

private const val SHARED_PREFS_NAME = "jet_notes_shared_prefs"
private const val THEME_KEY = "theme"
private const val TEXT_SIZE_KEY = "text_size"
private const val CORNET_STYLE_KEY = "corner_style"
private const val STYLE_KEY = "style"
private const val ORDER_TYPE_KEY = "order_type"

class SettingsRepositoryImpl(context: Context): SettingsRepository {

    private val sharedPreferences = context.getSharedPreferences(
        SHARED_PREFS_NAME,
        ComponentActivity.MODE_PRIVATE
    )

    override fun get(): SettingsBundle {
         return SettingsBundle(
            theme = JetNotesTheme.valueOf(
                sharedPreferences.getString(
                    THEME_KEY,
                    SettingsBundle().theme.name
                ) ?: SettingsBundle().theme.name
            ),
            size = JetNotesSize.valueOf(
                sharedPreferences.getString(
                    TEXT_SIZE_KEY,
                    SettingsBundle().size.name
                ) ?: SettingsBundle().size.name
            ),
            cornerStyle = JetNotesCorners.valueOf(
                sharedPreferences.getString(
                    CORNET_STYLE_KEY,
                    SettingsBundle().cornerStyle.name
                ) ?: SettingsBundle().cornerStyle.name
            ),
            style = JetNotesStyle.valueOf(
                sharedPreferences.getString(
                    STYLE_KEY,
                    SettingsBundle().style.name
                ) ?: SettingsBundle().style.name
            ),
            orderType = OrderType.valueOf(
                sharedPreferences.getString(
                    ORDER_TYPE_KEY,
                    SettingsBundle().orderType.name
                )  ?: SettingsBundle().orderType.name
            )
        )
    }

    override fun save(settingsBundle: SettingsBundle) {
        sharedPreferences.edit().apply {
            putString(THEME_KEY, settingsBundle.theme.name)
            putString(TEXT_SIZE_KEY, settingsBundle.size.name)
            putString(CORNET_STYLE_KEY, settingsBundle.cornerStyle.name)
            putString(STYLE_KEY, settingsBundle.style.name)
            putString(ORDER_TYPE_KEY, settingsBundle.orderType.name)
        }.apply()
    }
}