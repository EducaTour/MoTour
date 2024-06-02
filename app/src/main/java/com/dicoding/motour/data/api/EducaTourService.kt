package com.dicoding.motour.data.api

import com.dicoding.motour.data.model.landmark.detail.LandmarkDetailList
import com.dicoding.motour.data.model.landmark.list.LandmarkList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EducaTourService {
    @GET("landmark")
    suspend fun getLandmarkList(): Response<LandmarkList>

    @GET("landmark/{id}")
    suspend fun getLandmarkDetail(
        @Query(
            "id"
        ) id: Int
    ): Response<LandmarkDetailList>
}