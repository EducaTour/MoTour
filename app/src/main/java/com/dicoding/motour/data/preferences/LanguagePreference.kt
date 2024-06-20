package com.dicoding.motour.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "content_language")

class LanguagePreference(appContext: Context) {
    private val dataStore = appContext.datastore
    private val CONTENT_LANGUAGE = intPreferencesKey("language_id")

    var languageId: Flow<Int> = dataStore.data
        .map { data ->
            data[CONTENT_LANGUAGE] ?: 0

        }

    suspend fun changeLanguage(language: LanguageUtil.ContentLanguage) {
        dataStore.edit { data ->
            data[CONTENT_LANGUAGE] = language.ordinal
        }
    }
}