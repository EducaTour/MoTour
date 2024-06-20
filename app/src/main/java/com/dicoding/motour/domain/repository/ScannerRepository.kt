package com.dicoding.motour.domain.repository

import com.dicoding.motour.data.model.scanner.Data
import okhttp3.MultipartBody
import retrofit2.Response

interface ScannerRepository {
    suspend fun getScanner(file: MultipartBody.Part): Response<Data>
}