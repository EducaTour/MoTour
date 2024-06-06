package com.dicoding.motour.data.repository.landmark.list.datasource

import com.dicoding.motour.data.model.landmark.list.Landmark

interface LandmarkCacheDatasource {
    suspend fun getLandmarkFromCache(): List<Landmark?>
    suspend fun saveLandmarkToCache(landmarkList: List<Landmark?>)
}