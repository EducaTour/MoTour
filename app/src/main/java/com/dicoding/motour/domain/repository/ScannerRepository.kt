package com.dicoding.motour.domain.repository

import com.dicoding.motour.data.model.scanner.Data
import com.dicoding.motour.data.model.scanner.ScannerResponse
import okhttp3.MultipartBody
import retrofit2.Response
import java.io.File

interface ScannerRepository {
    suspend fun getScanner(file: MultipartBody.Part): Response<Data>
}