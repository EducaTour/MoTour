package com.dicoding.motour.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dicoding.motour.domain.usecase.GetLandmarkListUseCase
import com.dicoding.motour.domain.usecase.UpdateLandmarkListUsecase

class HomeViewModel(
    private val getLandmarkListUseCase: GetLandmarkListUseCase,
    private val updateLandmarkListUsecase: UpdateLandmarkListUsecase
) : ViewModel() {

    fun getLandmarkList() = liveData {
        val landmarkList = getLandmarkListUseCase.execute()
        emit(landmarkList)
    }

    fun updateLandmarkList() = liveData {
        val landmarkList = updateLandmarkListUsecase.execute()
        emit(landmarkList)
    }
}