package com.dicoding.motour.presentation.di.core

import com.dicoding.motour.data.repository.landmark.list.datasource.LandmarkCacheDatasource
import com.dicoding.motour.data.repository.landmark.list.datasourceImpl.LandmarkCacheDatasourceImpl
import dagger.Module
import dagger.Provides

@Module
class CacheDataModule {
    @Provides
    fun provideLandmarkCacheDataSource(): LandmarkCacheDatasource {
        return LandmarkCacheDatasourceImpl()
    }
}