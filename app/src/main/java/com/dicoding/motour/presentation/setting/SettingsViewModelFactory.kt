package com.dicoding.motour.presentation.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.motour.data.preferences.LanguagePreference

class SettingsViewModelFactory(
    private val languagePreference: LanguagePreference
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SettingsViewModel(languagePreference) as T
    }
}