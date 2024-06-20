package com.dicoding.motour.presentation.di.core

import com.dicoding.motour.presentation.di.home.HomeSubComponent
import com.dicoding.motour.presentation.di.landmark.LandmarkSubComponent
import com.dicoding.motour.presentation.di.scanner.ScannerSubComponent
import com.dicoding.motour.presentation.di.settings.SettingsSubComponent
import com.dicoding.motour.presentation.di.startup.StartupSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetModule::class,
        DataBaseModule::class,
        UseCaseModule::class,
        RepositoryModule::class,
        RemoteDataModule::class,
        LocalDataModule::class,
        CacheDataModule::class
    ]
)
interface AppComponent {
    fun startupSubComponent(): StartupSubComponent.Factory
    fun homeSubComponent(): HomeSubComponent.Factory
    fun scannerSubComponent(): ScannerSubComponent.Factory
    fun landmarkSubComponent(): LandmarkSubComponent.Factory
    fun settingsSubComponent(): SettingsSubComponent.Factory
}