package com.dicoding.motour.data.repository.landmark.list.datasourceImpl

import com.dicoding.motour.data.api.EducaTourService
import com.dicoding.motour.data.model.landmark.list.LandmarkList
import com.dicoding.motour.data.repository.landmark.list.datasource.LandmarkRemoteDatasource
import retrofit2.Response

class LandmarkRemoteDatasourceImpl(
    private val educaTourService: EducaTourService
) : LandmarkRemoteDatasource {
    override suspend fun getLandmarkList(): Response<LandmarkList> {
        return educaTourService.getLandmarkList()
    }
}