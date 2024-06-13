package com.dicoding.motour.data.api

import com.dicoding.motour.data.model.landmark.detail.LandmarkDetailData
import com.dicoding.motour.data.model.landmark.list.LandmarkList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EducaTourService {
    @GET("landmarks")
    suspend fun getLandmarkList(): Response<LandmarkList>

    @GET("landmarks/{id}")
    suspend fun getLandmarkDetail(
        @Path(
            "id"
        ) id: Int
    ): Response<LandmarkDetailData>
}