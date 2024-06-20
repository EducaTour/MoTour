package com.dicoding.motour.data.repository.scanner

import com.dicoding.motour.data.model.scanner.Data
import com.dicoding.motour.data.model.scanner.ScannerResponse
import okhttp3.MultipartBody
import retrofit2.Response
import java.io.File

interface ScannerRemoteDatasource {
    suspend fun getScanner(file: MultipartBody.Part): Response<Data>
}