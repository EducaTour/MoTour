package com.dicoding.motour.presentation.scanner

import androidx.lifecycle.ViewModel
import com.dicoding.motour.domain.usecase.GetScannerUseCase
import androidx.lifecycle.ViewModelProvider

class ScannerViewModelFactory(
    private val getScannerUseCase: GetScannerUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ScannerViewModel(getScannerUseCase) as T
    }
}