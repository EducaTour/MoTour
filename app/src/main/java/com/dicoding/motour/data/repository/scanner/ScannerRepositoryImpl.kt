package com.dicoding.motour.data.repository.scanner

import com.dicoding.motour.data.model.scanner.Data
import com.dicoding.motour.domain.repository.ScannerRepository
import okhttp3.MultipartBody
import retrofit2.Response

class ScannerRepositoryImpl(
    private val scannerRemoteDatasource: ScannerRemoteDatasource
) : ScannerRepository {
    override suspend fun getScanner(file: MultipartBody.Part): Response<Data> {
        return scannerRemoteDatasource.getScanner(file)
    }
}