package ru.itis.android.uprising26.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsManager @Inject constructor(
    @ApplicationContext context: Context
) {
    private val prefs = context.getSharedPreferences("settings_prefs", Context.MODE_PRIVATE)

    fun isInfoShown(): Boolean {
        return prefs.getBoolean("info_shown", false)
    }

    fun setInfoShown() {
        prefs.edit().putBoolean("info_shown", true).apply()
    }
}