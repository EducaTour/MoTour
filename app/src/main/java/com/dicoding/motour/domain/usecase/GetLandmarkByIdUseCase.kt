package com.dicoding.motour.domain.usecase

import com.dicoding.motour.data.model.landmark.detail.LandmarkDetail
import com.dicoding.motour.domain.repository.LandmarkDetailRepository

class GetLandmarkByIdUseCase(private val landmarkDetailRepository: LandmarkDetailRepository) {
    suspend fun execute(id: Int): LandmarkDetail? {
        return landmarkDetailRepository.getLandmarkById(id)
    }
}