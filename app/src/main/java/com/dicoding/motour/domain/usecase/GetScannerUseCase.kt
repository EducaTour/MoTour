package com.dicoding.motour.domain.usecase

import com.dicoding.motour.data.model.scanner.Data
import com.dicoding.motour.data.model.scanner.ScannerResponse
import com.dicoding.motour.domain.repository.ScannerRepository
import okhttp3.MultipartBody
import retrofit2.Response
import java.io.File

class GetScannerUseCase(private val scannerRepository: ScannerRepository) {
    suspend fun execute(file: MultipartBody.Part): Response<Data> {
        return scannerRepository.getScanner(file)
    }
}
