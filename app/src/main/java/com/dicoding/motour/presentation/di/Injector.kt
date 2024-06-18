package com.dicoding.motour.presentation.di

import com.dicoding.motour.presentation.di.home.HomeSubComponent
import com.dicoding.motour.presentation.di.scanner.ScannerSubComponent

interface Injector {
    fun createHomeSubComponent(): HomeSubComponent
    fun createScannerSubComponent(): ScannerSubComponent
}