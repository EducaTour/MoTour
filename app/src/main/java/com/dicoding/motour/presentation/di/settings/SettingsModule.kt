package com.dicoding.motour.presentation.di.settings

import com.dicoding.motour.data.preferences.LanguagePreference
import com.dicoding.motour.presentation.setting.SettingsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class SettingsModule {

    @SettingsScope
    @Provides
    fun provideSettingsViewModelFactory(
        languagePreference: LanguagePreference
    ): SettingsViewModelFactory {
        return SettingsViewModelFactory(
            languagePreference
        )
    }
}