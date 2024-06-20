package com.dicoding.motour.presentation.setting

import androidx.lifecycle.ViewModel
import com.dicoding.motour.data.preferences.LanguagePreference
import com.dicoding.motour.data.preferences.LanguageUtil

class SettingsViewModel(
    private val languagePreference: LanguagePreference
) : ViewModel() {
    suspend fun changePreferredLanguage(language: LanguageUtil.ContentLanguage) {
        languagePreference.changeLanguage(language)
    }
}