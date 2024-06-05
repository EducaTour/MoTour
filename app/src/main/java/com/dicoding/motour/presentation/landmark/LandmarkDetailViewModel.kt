package com.dicoding.motour.presentation.landmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dicoding.motour.domain.usecase.GetLandmarkByIdUseCase
import com.dicoding.motour.domain.usecase.UpdateLandmarkByIdUseCase
import com.dicoding.motour.domain.usecase.UpdateLandmarkListUsecase

class LandmarkDetailViewModel(
    private val getLandmarkByIdUseCase: GetLandmarkByIdUseCase,
    private val updateLandmarkByIdUseCase: UpdateLandmarkByIdUseCase,
) : ViewModel() {
    fun getLandmarkById(id: Int) = liveData {
        val landmark = getLandmarkByIdUseCase.execute(id)
        emit(landmark)
    }

    fun updateLandmarkById(id: Int) = liveData {
        val landmark = updateLandmarkByIdUseCase.execute(id)
        emit(landmark)
    }
}