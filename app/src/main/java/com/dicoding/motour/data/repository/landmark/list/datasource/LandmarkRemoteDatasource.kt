package com.dicoding.motour.data.repository.landmark.list.datasource

import com.dicoding.motour.data.model.landmark.list.LandmarkList
import retrofit2.Response

interface LandmarkRemoteDatasource {
    suspend fun getLandmarkList(): Response<LandmarkList>
}