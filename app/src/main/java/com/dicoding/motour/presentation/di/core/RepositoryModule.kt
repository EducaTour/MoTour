package com.dicoding.motour.presentation.di.core

import com.dicoding.motour.data.repository.landmark.detail.LandmarkDetailRepositoryImpl
import com.dicoding.motour.data.repository.landmark.detail.datasource.LandmarkDetailRemoteDatasource
import com.dicoding.motour.data.repository.landmark.list.LandmarkRepositoryImpl
import com.dicoding.motour.data.repository.landmark.list.datasource.LandmarkCacheDatasource
import com.dicoding.motour.data.repository.landmark.list.datasource.LandmarkLocalDatasource
import com.dicoding.motour.data.repository.landmark.list.datasource.LandmarkRemoteDatasource
import com.dicoding.motour.domain.repository.LandmarkDetailRepository
import com.dicoding.motour.domain.repository.LandmarkRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideLandmarkRepository(
        landmarkCacheDatasource: LandmarkCacheDatasource,
        landmarkLocalDatasource: LandmarkLocalDatasource,
        landmarkRemoteDatasource: LandmarkRemoteDatasource
    ): LandmarkRepository {
        return LandmarkRepositoryImpl(
            landmarkCacheDatasource,
            landmarkLocalDatasource,
            landmarkRemoteDatasource
        )
    }

    @Provides
    fun provideLandmarkDetailRepository(
        landmarkDetailRemoteDatasource: LandmarkDetailRemoteDatasource
    ): LandmarkDetailRepository {
        return LandmarkDetailRepositoryImpl(
            landmarkDetailRemoteDatasource
        )
    }
}