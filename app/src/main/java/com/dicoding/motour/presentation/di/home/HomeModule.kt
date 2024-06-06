package com.dicoding.motour.presentation.di.home

import com.dicoding.motour.domain.usecase.GetLandmarkListUseCase
import com.dicoding.motour.domain.usecase.UpdateLandmarkListUsecase
import com.dicoding.motour.presentation.home.HomeViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class HomeModule {
    @HomeScope
    @Provides
    fun provideHomeViewModelFactory(
        getLandmarkListUseCase: GetLandmarkListUseCase,
        updateLandmarkListUsecase: UpdateLandmarkListUsecase
    ): HomeViewModelFactory {
        return HomeViewModelFactory(
            getLandmarkListUseCase,
            updateLandmarkListUsecase
        )
    }
}