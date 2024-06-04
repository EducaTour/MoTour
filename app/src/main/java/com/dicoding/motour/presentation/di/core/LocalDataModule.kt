package com.dicoding.motour.presentation.di.core

import com.dicoding.motour.data.db.dao.landmark.list.LandmarkDao
import com.dicoding.motour.data.repository.landmark.list.datasource.LandmarkLocalDatasource
import com.dicoding.motour.data.repository.landmark.list.datasourceImpl.LandmarkLocalDatasourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {
    @Provides
    fun provideLandmarkLocalDataSource(landmarkDao: LandmarkDao): LandmarkLocalDatasource {
        return LandmarkLocalDatasourceImpl(landmarkDao)
    }
}