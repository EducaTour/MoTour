package com.dicoding.motour.presentation.di.core

import android.content.Context
import com.dicoding.motour.presentation.di.home.HomeSubComponent
import com.dicoding.motour.presentation.di.scanner.ScannerSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [
    HomeSubComponent::class,
    ScannerSubComponent::class
])
class AppModule(private val context: Context) {
    @Singleton
    @Provides
    fun provideApplicationContext(): Context {
        return context.applicationContext
    }
}