package com.dicoding.motour.domain.repository

import com.dicoding.motour.data.model.landmark.list.Landmark

interface LandmarkRepository {
    suspend fun getLandmarkList(): List<Landmark?>?
    suspend fun updateLandmarkList(): List<Landmark?>?
}