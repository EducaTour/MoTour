package com.dicoding.motour.presentation.di

import com.dicoding.motour.presentation.di.home.HomeSubComponent
import com.dicoding.motour.presentation.di.landmark.LandmarkSubComponent
import com.dicoding.motour.presentation.di.scanner.ScannerSubComponent
import com.dicoding.motour.presentation.di.settings.SettingsSubComponent
import com.dicoding.motour.presentation.di.startup.StartupSubComponent

interface Injector {
    fun createStartupSubComponent(): StartupSubComponent
    fun createHomeSubComponent(): HomeSubComponent
    fun createScannerSubComponent(): ScannerSubComponent
    fun createLandmarkSubComponent(): LandmarkSubComponent
    fun createSettingsSubComponent(): SettingsSubComponent
}