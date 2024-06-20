package com.dicoding.motour.presentation.di.core

import android.content.Context
import com.dicoding.motour.data.preferences.LanguagePreference
import com.dicoding.motour.presentation.di.home.HomeSubComponent
import com.dicoding.motour.presentation.di.landmark.LandmarkSubComponent
import com.dicoding.motour.presentation.di.scanner.ScannerSubComponent
import com.dicoding.motour.presentation.di.settings.SettingsSubComponent
import com.dicoding.motour.presentation.di.startup.StartupSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    subcomponents = [
        HomeSubComponent::class,
        ScannerSubComponent::class,
        LandmarkSubComponent::class,
        SettingsSubComponent::class,
        StartupSubComponent::class
    ]
)
class AppModule(private val context: Context) {
    @Singleton
    @Provides
    fun provideApplicationContext(): Context {
        return context.applicationContext
    }

    @Provides
    @Singleton
    fun provideLanguagePreference(context: Context): LanguagePreference {
        return LanguagePreference(context)
    }
}