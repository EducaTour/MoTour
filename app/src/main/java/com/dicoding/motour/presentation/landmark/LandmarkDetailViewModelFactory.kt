package com.dicoding.motour.presentation.landmark

import androidx.lifecycle.ViewModelProvider
import com.dicoding.motour.data.preferences.LanguagePreference
import com.dicoding.motour.domain.usecase.GetLandmarkByIdUseCase
import com.dicoding.motour.domain.usecase.UpdateLandmarkByIdUseCase

class LandmarkDetailViewModelFactory(
    private val getLandmarkByIdUseCase: GetLandmarkByIdUseCase,
    private val updateLandmarkByIdUseCase: UpdateLandmarkByIdUseCase,
    private val languagePreference: LanguagePreference
) : ViewModelProvider.Factory {
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        return LandmarkDetailViewModel(
            getLandmarkByIdUseCase,
            updateLandmarkByIdUseCase,
            languagePreference
        ) as T
    }
}