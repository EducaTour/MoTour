package com.dicoding.motour.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dicoding.motour.data.model.landmark.list.Landmark
import com.dicoding.motour.domain.usecase.GetLandmarkListUseCase
import com.dicoding.motour.domain.usecase.UpdateLandmarkListUsecase

class HomeViewModel(
    private val getLandmarkListUseCase: GetLandmarkListUseCase,
    private val updateLandmarkListUsecase: UpdateLandmarkListUsecase
) : ViewModel() {
    sealed class LandmarkListState<out R> {
        data object Loading : LandmarkListState<Nothing>()
        data class Result<out T>(val landmarkList: T) : LandmarkListState<T>()
    }

    fun getLandmarkList() = liveData {
        emit(LandmarkListState.Loading)
        val landmarkList = getLandmarkListUseCase.execute()
        emit(LandmarkListState.Result<List<Landmark?>?>(landmarkList))
    }

    fun updateLandmarkList() = liveData {
        val landmarkList = updateLandmarkListUsecase.execute()
        emit(landmarkList)
    }
}