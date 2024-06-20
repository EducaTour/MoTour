package com.dicoding.motour.data.api

import com.dicoding.motour.data.model.landmark.detail.LandmarkDetailData
import com.dicoding.motour.data.model.landmark.list.LandmarkList
import com.dicoding.motour.data.model.scanner.Data
import com.dicoding.motour.data.model.scanner.ScannerResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface EducaTourService {
    @GET("landmarks")
    suspend fun getLandmarkList(): Response<LandmarkList>

    @GET("landmarks/{id}")
    suspend fun getLandmarkDetail(
        @Path(
            "id"
        ) id: Int
    ): Response<LandmarkDetailData>

    @Multipart
    @POST("predictions/")
    suspend fun getScanner(
        @Part file: MultipartBody.Part,
    ): Response<Data>

}