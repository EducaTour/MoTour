package com.dicoding.motour.presentation.di.core

import com.dicoding.motour.data.api.EducaTourService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetModule(private val baseUrl: String) {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

    @Singleton
    @Provides
    fun provideEducaTourService(retrofit: Retrofit): EducaTourService {
        return retrofit.create(EducaTourService::class.java)
    }
}