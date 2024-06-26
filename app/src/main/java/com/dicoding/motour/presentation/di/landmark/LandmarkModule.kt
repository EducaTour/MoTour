package com.dicoding.motour.presentation.di.landmark

import com.dicoding.motour.data.preferences.LanguagePreference
import com.dicoding.motour.domain.usecase.GetLandmarkByIdUseCase
import com.dicoding.motour.domain.usecase.UpdateLandmarkByIdUseCase
import com.dicoding.motour.presentation.landmark.LandmarkDetailViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class LandmarkModule {

    @LandmarkScope
    @Provides
    fun provideLandmarkViewModelFactory(
        getLandmarkByIdUseCase: GetLandmarkByIdUseCase,
        updateLandmarkByIdUseCase: UpdateLandmarkByIdUseCase,
        languagePreference: LanguagePreference
    ): LandmarkDetailViewModelFactory {
        return LandmarkDetailViewModelFactory(
            getLandmarkByIdUseCase,
            updateLandmarkByIdUseCase,
            languagePreference
        )
    }
}