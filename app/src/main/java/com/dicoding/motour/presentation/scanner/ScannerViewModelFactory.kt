package com.dicoding.motour.presentation.scanner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.motour.domain.usecase.GetScannerUseCase

class ScannerViewModelFactory(
    private val getScannerUseCase: GetScannerUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ScannerViewModel(getScannerUseCase) as T
    }
}