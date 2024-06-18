package com.dicoding.motour.data.repository.landmark.detail

import android.util.Log
import com.dicoding.motour.data.model.landmark.detail.LandmarkDetail
import com.dicoding.motour.data.repository.landmark.detail.datasource.LandmarkDetailRemoteDatasource
import com.dicoding.motour.domain.repository.LandmarkDetailRepository

class LandmarkDetailRepositoryImpl(
    private val landmarkDetailRemoteDatasource: LandmarkDetailRemoteDatasource
) : LandmarkDetailRepository {
    override suspend fun updateLandmarkById(id: Int): LandmarkDetail? {
        // not yet
        return null
    }

    override suspend fun getLandmarkById(id: Int): LandmarkDetail? {
        return getLandmarkFromAPI(id)
    }

    private suspend fun getLandmarkFromAPI(id: Int): LandmarkDetail? {
        var landmark: LandmarkDetail? = null
        try {
            val response = landmarkDetailRemoteDatasource.getLandmarkDetail(id)
            val body = response.body()
            if (body != null) {
                landmark = body.landmark
            }
        } catch (e: Exception) {

        }
        return landmark
    }
}