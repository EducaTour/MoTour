package com.dicoding.motour.data.repository.landmark.list

import android.util.Log
import com.dicoding.motour.data.model.landmark.list.Landmark
import com.dicoding.motour.data.repository.landmark.list.datasource.LandmarkCacheDatasource
import com.dicoding.motour.data.repository.landmark.list.datasource.LandmarkLocalDatasource
import com.dicoding.motour.data.repository.landmark.list.datasource.LandmarkRemoteDatasource
import com.dicoding.motour.domain.repository.LandmarkRepository

class LandmarkRepositoryImpl(
    private val landmarkCacheDatasource: LandmarkCacheDatasource,
    private val landmarkLocalDatasource: LandmarkLocalDatasource,
    private val landmarkRemoteDatasource: LandmarkRemoteDatasource
) : LandmarkRepository {
    override suspend fun getLandmarkList(): List<Landmark?> {
        return getLandmarkFromCache()
    }

    override suspend fun updateLandmarkList(): List<Landmark> {
        val landmarkList = getLandmarkFromAPI()
        landmarkLocalDatasource.deleteLandmarkFromDB()
        landmarkLocalDatasource.saveLandmarkToDB(landmarkList)
        landmarkCacheDatasource.saveLandmarkToCache(landmarkList)
        return landmarkList
    }

    private suspend fun getLandmarkFromAPI(): List<Landmark> {
        var landmarkList = emptyList<Landmark>()
        try {
            val response = landmarkRemoteDatasource.getLandmarkList()
            val body = response.body()
            if (body != null) {
                landmarkList = body.landmarks
            }
        } catch (e: Exception) {
            Log.i("LandmarkRepositoryImpl", "Error: ${e.message}")
        }
        return landmarkList
    }

    private suspend fun getLandmarkFromDB(): List<Landmark?> {
        lateinit var landmarkList: List<Landmark?>
        try {
            landmarkList = landmarkLocalDatasource.getLandmarkFromDB()
        } catch (e: Exception) {
            Log.i("LandmarkRepositoryImpl", "Error: ${e.message}")
        }

        if (landmarkList.isNotEmpty()) {
            return landmarkList
        } else {
            landmarkList = getLandmarkFromAPI()
            landmarkLocalDatasource.saveLandmarkToDB(landmarkList)
        }

        return landmarkList
    }

    private suspend fun getLandmarkFromCache(): List<Landmark?> {
        lateinit var landmarkList: List<Landmark?>
        try {
            landmarkList = landmarkCacheDatasource.getLandmarkFromCache()
        } catch (e: Exception) {
            Log.i("LandmarkRepositoryImpl", "Error: ${e.message}")
        }

        if (landmarkList.isNotEmpty()) {
            return landmarkList
        } else {
            landmarkList = getLandmarkFromDB()
            landmarkCacheDatasource.saveLandmarkToCache(landmarkList)
        }

        return landmarkList
    }
}