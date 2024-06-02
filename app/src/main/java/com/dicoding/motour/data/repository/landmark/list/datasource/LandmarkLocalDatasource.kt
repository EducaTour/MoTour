package com.dicoding.motour.data.repository.landmark.list.datasource

import com.dicoding.motour.data.model.landmark.list.Landmark

interface LandmarkLocalDatasource {
    suspend fun getLandmarkFromDB(): List<Landmark?>
    suspend fun saveLandmarkToDB(landmarkList: List<Landmark?>)
    suspend fun deleteLandmarkFromDB()
}