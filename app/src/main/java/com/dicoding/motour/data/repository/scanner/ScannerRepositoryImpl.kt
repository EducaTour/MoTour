package com.dicoding.motour.data.repository.scanner

import com.dicoding.motour.data.model.scanner.ScannerResponse
import com.dicoding.motour.domain.repository.ScannerRepository
import okhttp3.MultipartBody
import retrofit2.Response
import java.io.File

class ScannerRepositoryImpl(
    private val scannerRemoteDatasource: ScannerRemoteDatasource
) : ScannerRepository {
    override suspend fun getScanner(file: MultipartBody.Part): Response<ScannerResponse> {
        return scannerRemoteDatasource.getScanner(file)
    }
}