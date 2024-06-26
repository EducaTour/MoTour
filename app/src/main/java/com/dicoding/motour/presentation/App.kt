package com.dicoding.motour.presentation

import android.app.Application
import com.dicoding.motour.BuildConfig
import com.dicoding.motour.presentation.di.Injector
import com.dicoding.motour.presentation.di.core.AppComponent
import com.dicoding.motour.presentation.di.core.AppModule
import com.dicoding.motour.presentation.di.core.DaggerAppComponent
import com.dicoding.motour.presentation.di.core.NetModule
import com.dicoding.motour.presentation.di.core.RemoteDataModule
import com.dicoding.motour.presentation.di.home.HomeSubComponent
import com.dicoding.motour.presentation.di.landmark.LandmarkSubComponent
import com.dicoding.motour.presentation.di.scanner.ScannerSubComponent
import com.dicoding.motour.presentation.di.settings.SettingsSubComponent
import com.dicoding.motour.presentation.di.startup.StartupSubComponent
import com.jakewharton.threetenabp.AndroidThreeTen


class App : Application(), Injector {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .netModule(NetModule(BuildConfig.BASE_URL))
            .remoteDataModule(RemoteDataModule())
            .build()
    }

    override fun createHomeSubComponent(): HomeSubComponent {
        return appComponent.homeSubComponent().create()
    }

    override fun createScannerSubComponent(): ScannerSubComponent {
        return appComponent.scannerSubComponent().create()
    }

    override fun createLandmarkSubComponent(): LandmarkSubComponent {
        return appComponent.landmarkSubComponent().create()
    }

    override fun createSettingsSubComponent(): SettingsSubComponent {
        return appComponent.settingsSubComponent().create()
    }

    override fun createStartupSubComponent(): StartupSubComponent {
        return appComponent.startupSubComponent().create()
    }
}