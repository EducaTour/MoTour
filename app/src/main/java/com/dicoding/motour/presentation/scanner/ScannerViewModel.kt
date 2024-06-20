package com.dicoding.motour.presentation.scanner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dicoding.motour.domain.usecase.GetScannerUseCase
import com.dicoding.motour.utils.Result
import okhttp3.MultipartBody

class ScannerViewModel(
    private val getScannerUseCase: GetScannerUseCase
) : ViewModel() {
    fun getScanner(file: MultipartBody.Part) = liveData {
        emit(Result.Loading)
        try {
            val response = getScannerUseCase.execute(file)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}
