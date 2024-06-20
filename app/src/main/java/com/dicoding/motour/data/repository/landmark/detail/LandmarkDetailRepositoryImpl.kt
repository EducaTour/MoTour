package com.dicoding.motour.data.repository.landmark.detail

import com.dicoding.motour.data.model.landmark.detail.LandmarkDetail
import com.dicoding.motour.data.preferences.LanguageUtil
import com.dicoding.motour.data.repository.landmark.detail.datasource.LandmarkDetailRemoteDatasource
import com.dicoding.motour.domain.repository.LandmarkDetailRepository

class LandmarkDetailRepositoryImpl(
    private val landmarkDetailRemoteDatasource: LandmarkDetailRemoteDatasource
) : LandmarkDetailRepository {
    override suspend fun updateLandmarkById(id: Int): LandmarkDetail? {
        return null
    }

    override suspend fun getLandmarkById(
        id: Int,
        language: LanguageUtil.ContentLanguage
    ): LandmarkDetail? {
        return getLandmarkFromAPI(id, language)
    }

    private suspend fun getLandmarkFromAPI(
        id: Int,
        language: LanguageUtil.ContentLanguage
    ): LandmarkDetail? {
        var landmark: LandmarkDetail? = null
        try {
            val response = landmarkDetailRemoteDatasource.getLandmarkDetail(id, language)
            val body = response.body()
            if (body != null) {
                landmark = body.landmark
            }
        } catch (e: Exception) {
            throw Exception(e)
        }

        return landmark
    }
}