package com.dicoding.motour.presentation.di.scanner

import com.dicoding.motour.domain.usecase.GetScannerUseCase
import com.dicoding.motour.presentation.scanner.ScannerViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ScannerModule {
    @ScannerScope
    @Provides
    fun provideScannerViewModelFactory(
        getScannerUseCase: GetScannerUseCase
    ): ScannerViewModelFactory {
        return ScannerViewModelFactory(getScannerUseCase)
    }
}
