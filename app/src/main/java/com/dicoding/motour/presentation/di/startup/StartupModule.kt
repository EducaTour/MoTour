package com.dicoding.motour.presentation.di.startup

import com.dicoding.motour.data.preferences.LanguagePreference
import com.dicoding.motour.presentation.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class StartupModule {

    @StartupScope
    @Provides
    fun provideMainViewModelFactory(
        languagePreference: LanguagePreference
    ): MainViewModelFactory {
        return MainViewModelFactory(
            languagePreference
        )
    }
}