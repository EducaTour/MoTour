package com.dicoding.motour.presentation

import android.app.Application
import com.dicoding.motour.presentation.di.Injector
import com.dicoding.motour.presentation.di.core.AppComponent
import com.dicoding.motour.presentation.di.core.AppModule
import com.dicoding.motour.presentation.di.core.NetModule
import com.dicoding.motour.presentation.di.core.RemoteDataModule
import com.dicoding.motour.presentation.di.home.HomeSubComponent
import com.dicoding.motour.BuildConfig
import com.dicoding.motour.presentation.di.core.DaggerAppComponent

class App : Application(), Injector {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .netModule(NetModule(BuildConfig.BASE_URL))
            .remoteDataModule(RemoteDataModule())
            .build()
    }
    override fun createHomeSubComponent(): HomeSubComponent {
        return appComponent.homeSubComponent().create()
    }
}