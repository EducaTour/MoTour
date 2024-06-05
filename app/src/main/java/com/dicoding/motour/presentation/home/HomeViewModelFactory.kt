package com.dicoding.motour.presentation.home

import androidx.lifecycle.ViewModelProvider
import com.dicoding.motour.domain.usecase.GetLandmarkListUseCase
import com.dicoding.motour.domain.usecase.UpdateLandmarkListUsecase

class HomeViewModelFactory(
    private val getLandmarkListUseCase: GetLandmarkListUseCase,
    private val updateLandmarkListUsecase: UpdateLandmarkListUsecase
) : ViewModelProvider.Factory {
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(
            getLandmarkListUseCase,
            updateLandmarkListUsecase
        ) as T
    }
}