package com.dicoding.motour.presentation.di.core

import com.dicoding.motour.data.repository.landmark.list.datasource.LandmarkCacheDatasource
import com.dicoding.motour.data.repository.landmark.list.datasourceImpl.LandmarkCacheDatasourceImpl
import dagger.Provides
import javax.inject.Singleton

class CacheDataModule {

    @Singleton
    @Provides
    fun provideLandmarkCacheDataSource(): LandmarkCacheDatasource {
        return LandmarkCacheDatasourceImpl()
    }
}