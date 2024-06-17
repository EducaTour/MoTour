package com.dicoding.motour.data.repository.landmark.detail.datasourceImpl

import com.dicoding.motour.data.api.EducaTourService
import com.dicoding.motour.data.model.landmark.detail.LandmarkDetailData
import com.dicoding.motour.data.repository.landmark.detail.datasource.LandmarkDetailRemoteDatasource
import retrofit2.Response

class LandmarkDetailRemoteDatasourceImpl(
    private val educaTourService: EducaTourService
) : LandmarkDetailRemoteDatasource {
    override suspend fun getLandmarkDetail(id: Int): Response<LandmarkDetailData> {
        return educaTourService.getLandmarkDetail(id)
    }
}