package com.dicoding.motour.presentation.di.core

import com.dicoding.motour.data.api.EducaTourService
import com.dicoding.motour.data.repository.landmark.list.datasource.LandmarkRemoteDatasource
import com.dicoding.motour.data.repository.landmark.list.datasourceImpl.LandmarkRemoteDatasourceImpl
import dagger.Provides
import javax.inject.Singleton

class RemoteDataModule {

    @Singleton
    @Provides
    fun provideLandmarkRemoteDataSource(educaTourService: EducaTourService): LandmarkRemoteDatasource {
        return LandmarkRemoteDatasourceImpl(
            educaTourService
        )
    }
}