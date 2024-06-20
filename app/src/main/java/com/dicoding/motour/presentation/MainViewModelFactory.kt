package com.dicoding.motour.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.motour.data.preferences.LanguagePreference

class MainViewModelFactory(
    private val languagePreference: LanguagePreference
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(languagePreference) as T
    }
}