package com.calculator.scientific.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

enum class AppTheme { LIGHT, DARK, SYSTEM }
enum class AppLanguage { ENGLISH, INDONESIAN }

class SettingsDataStore(private val context: Context) {

    companion object {
        private val THEME_KEY = stringPreferencesKey("theme")
        private val LANGUAGE_KEY = stringPreferencesKey("language")
    }

    val themeFlow: Flow<AppTheme> = context.dataStore.data.map { preferences ->
        when (preferences[THEME_KEY]) {
            "light" -> AppTheme.LIGHT
            "dark" -> AppTheme.DARK
            else -> AppTheme.SYSTEM
        }
    }

    val languageFlow: Flow<AppLanguage> = context.dataStore.data.map { preferences ->
        when (preferences[LANGUAGE_KEY]) {
            "indonesian" -> AppLanguage.INDONESIAN
            else -> AppLanguage.ENGLISH
        }
    }

    suspend fun setTheme(theme: AppTheme) {
        context.dataStore.edit { preferences ->
            preferences[THEME_KEY] = when (theme) {
                AppTheme.LIGHT -> "light"
                AppTheme.DARK -> "dark"
                AppTheme.SYSTEM -> "system"
            }
        }
    }

    suspend fun setLanguage(language: AppLanguage) {
        context.dataStore.edit { preferences ->
            preferences[LANGUAGE_KEY] = when (language) {
                AppLanguage.ENGLISH -> "english"
                AppLanguage.INDONESIAN -> "indonesian"
            }
        }
    }
}
