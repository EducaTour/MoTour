package com.dicoding.motour.data.repository.scanner

import com.dicoding.motour.data.api.EducaTourService
import com.dicoding.motour.data.model.scanner.Data
import com.dicoding.motour.data.model.scanner.ScannerResponse
import okhttp3.MultipartBody
import retrofit2.Response

class ScannerRemoteDatasourceImpl(
    private val educaTourService: EducaTourService,

) : ScannerRemoteDatasource {
    override suspend fun getScanner(file: MultipartBody.Part): Response<Data> {
        return educaTourService.getScanner(file)
    }
}