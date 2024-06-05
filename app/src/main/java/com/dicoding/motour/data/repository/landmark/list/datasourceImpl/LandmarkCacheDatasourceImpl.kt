package com.dicoding.motour.data.repository.landmark.list.datasourceImpl

import com.dicoding.motour.data.model.landmark.list.Landmark
import com.dicoding.motour.data.repository.landmark.list.datasource.LandmarkCacheDatasource

class LandmarkCacheDatasourceImpl : LandmarkCacheDatasource {
    private var landmarkList = ArrayList<Landmark?>()
    override suspend fun getLandmarkFromCache(): List<Landmark?> {
        return landmarkList
    }

    override suspend fun saveLandmarkToCache(landmarkList: List<Landmark?>) {
        this.landmarkList.clear()
        this.landmarkList = ArrayList(landmarkList)
    }
}