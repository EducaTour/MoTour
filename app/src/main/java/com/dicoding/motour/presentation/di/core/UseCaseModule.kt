package com.dicoding.motour.presentation.di.core

import com.dicoding.motour.domain.repository.LandmarkDetailRepository
import com.dicoding.motour.domain.repository.LandmarkRepository
import com.dicoding.motour.domain.repository.ScannerRepository
import com.dicoding.motour.domain.usecase.GetLandmarkByIdUseCase
import com.dicoding.motour.domain.usecase.GetLandmarkListUseCase
import com.dicoding.motour.domain.usecase.GetScannerUseCase
import com.dicoding.motour.domain.usecase.UpdateLandmarkByIdUseCase
import com.dicoding.motour.domain.usecase.UpdateLandmarkListUsecase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetLandmarkListUseCase(landmarkRepository: LandmarkRepository): GetLandmarkListUseCase {
        return GetLandmarkListUseCase(landmarkRepository)
    }

    @Provides
    fun provideUpdateLandmarkListUseCase(landmarkRepository: LandmarkRepository): UpdateLandmarkListUsecase {
        return UpdateLandmarkListUsecase(landmarkRepository)
    }

    @Provides
    fun provideGetLandmarkByIdUseCase(landmarkDetailRepository: LandmarkDetailRepository): GetLandmarkByIdUseCase {
        return GetLandmarkByIdUseCase(landmarkDetailRepository)
    }

    @Provides
    fun provideUpdateLandmarkByIdUseCase(landmarkDetailRepository: LandmarkDetailRepository): UpdateLandmarkByIdUseCase {
        return UpdateLandmarkByIdUseCase(landmarkDetailRepository)
    }

    @Provides
    fun provideGetScannerUseCase(scannerRepository: ScannerRepository): GetScannerUseCase {
        return GetScannerUseCase(scannerRepository)
    }
}