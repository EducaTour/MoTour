package com.dicoding.motour.presentation.di.core

import com.dicoding.motour.data.api.EducaTourService
import com.dicoding.motour.data.repository.landmark.detail.datasource.LandmarkDetailRemoteDatasource
import com.dicoding.motour.data.repository.landmark.detail.datasourceImpl.LandmarkDetailRemoteDatasourceImpl
import com.dicoding.motour.data.repository.landmark.list.datasource.LandmarkRemoteDatasource
import com.dicoding.motour.data.repository.landmark.list.datasourceImpl.LandmarkRemoteDatasourceImpl
import dagger.Module
import dagger.Provides

@Module
class RemoteDataModule {
    @Provides
    fun provideLandmarkRemoteDataSource(educaTourService: EducaTourService): LandmarkRemoteDatasource {
        return LandmarkRemoteDatasourceImpl(
            educaTourService
        )
    }

    @Provides
    fun provideLandmarkDetailRemoteDataSource(educaTourService: EducaTourService): LandmarkDetailRemoteDatasource {
        return LandmarkDetailRemoteDatasourceImpl(
            educaTourService
        )
    }
}