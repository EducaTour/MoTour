package com.dicoding.motour.presentation.di.core

import com.dicoding.motour.data.repository.landmark.list.LandmarkRepositoryImpl
import com.dicoding.motour.data.repository.landmark.list.datasource.LandmarkCacheDatasource
import com.dicoding.motour.data.repository.landmark.list.datasource.LandmarkLocalDatasource
import com.dicoding.motour.data.repository.landmark.list.datasource.LandmarkRemoteDatasource
import com.dicoding.motour.domain.repository.LandmarkRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

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
}