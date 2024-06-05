package com.dicoding.motour.domain.usecase

import com.dicoding.motour.data.model.landmark.list.Landmark
import com.dicoding.motour.domain.repository.LandmarkRepository

class GetLandmarkListUseCase(private val landmarkRepository: LandmarkRepository) {
    suspend fun execute() : List<Landmark?>? {
        return landmarkRepository.getLandmarkList()
    }
}