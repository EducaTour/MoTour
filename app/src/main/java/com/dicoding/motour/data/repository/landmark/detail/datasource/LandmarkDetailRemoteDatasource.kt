package com.dicoding.motour.data.repository.landmark.detail.datasource

import com.dicoding.motour.data.model.landmark.detail.LandmarkDetailData
import com.dicoding.motour.data.preferences.LanguageUtil
import retrofit2.Response

interface LandmarkDetailRemoteDatasource {
    suspend fun getLandmarkDetail(
        id: Int,
        language: LanguageUtil.ContentLanguage
    ): Response<LandmarkDetailData>
}