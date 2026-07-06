package com.calculator.scientific.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.calculator.scientific.data.AppLanguage
import com.calculator.scientific.data.AppTheme
import com.calculator.scientific.data.SettingsDataStore
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val settingsDataStore = SettingsDataStore(application)

    val theme: StateFlow<AppTheme> = settingsDataStore.themeFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AppTheme.SYSTEM)

    val language: StateFlow<AppLanguage> = settingsDataStore.languageFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AppLanguage.ENGLISH)

    fun setTheme(theme: AppTheme) {
        viewModelScope.launch {
            settingsDataStore.setTheme(theme)
        }
    }

    fun setLanguage(language: AppLanguage) {
        viewModelScope.launch {
            settingsDataStore.setLanguage(language)
        }
    }

    companion object {
        fun getLanguageCode(language: AppLanguage): String {
            return when (language) {
                AppLanguage.ENGLISH -> "en"
                AppLanguage.INDONESIAN -> "in"
            }
        }
    }
}
