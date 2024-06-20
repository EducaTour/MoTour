package com.dicoding.motour.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dicoding.motour.data.preferences.LanguagePreference
import com.dicoding.motour.data.preferences.LanguageUtil
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class MainViewModel(
    private val languagePreference: LanguagePreference
) : ViewModel() {

    fun getPreferredLanguage() = liveData {
        runBlocking {
            val language = languagePreference.languageId.first()
            emit(LanguageUtil.ContentLanguage.entries[language])
        }
    }
}