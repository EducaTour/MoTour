package com.dicoding.motour.domain.usecase

import com.dicoding.motour.data.model.landmark.detail.LandmarkDetail
import com.dicoding.motour.domain.repository.LandmarkDetailRepository

class UpdateLandmarkByIdUseCase (private val landmarkDetailRepository: LandmarkDetailRepository){
    suspend fun execute(id: Int): LandmarkDetail? {
        return landmarkDetailRepository.updateLandmarkById(id)
    }
}