package com.dicoding.motour.domain.repository

import com.dicoding.motour.data.model.landmark.detail.LandmarkDetail
import com.dicoding.motour.data.preferences.LanguageUtil

interface LandmarkDetailRepository {
    suspend fun updateLandmarkById(id: Int): LandmarkDetail?
    suspend fun getLandmarkById(id: Int, language: LanguageUtil.ContentLanguage): LandmarkDetail?
}