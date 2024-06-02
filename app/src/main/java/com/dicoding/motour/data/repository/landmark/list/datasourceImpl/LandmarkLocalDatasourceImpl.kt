package com.dicoding.motour.data.repository.landmark.list.datasourceImpl

import com.dicoding.motour.data.db.dao.landmark.list.LandmarkDao
import com.dicoding.motour.data.model.landmark.list.Landmark
import com.dicoding.motour.data.repository.landmark.list.datasource.LandmarkLocalDatasource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LandmarkLocalDatasourceImpl (private val landmarkDao: LandmarkDao) : LandmarkLocalDatasource {
    override suspend fun getLandmarkFromDB(): List<Landmark?> {
        return landmarkDao.getAllLandmark()
    }

    override suspend fun saveLandmarkToDB(landmarkList: List<Landmark?>) {
        CoroutineScope(Dispatchers.IO).launch {
            landmarkDao.insert(landmarkList)
        }
    }

    override suspend fun deleteLandmarkFromDB() {
        CoroutineScope(Dispatchers.IO).launch {
            landmarkDao.deleteAllLandmark()
        }
    }
}