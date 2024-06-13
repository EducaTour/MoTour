package com.dicoding.motour.presentation.di

import com.dicoding.motour.presentation.di.home.HomeSubComponent
import com.dicoding.motour.presentation.di.landmark.LandmarkSubComponent

interface Injector {
    fun createHomeSubComponent(): HomeSubComponent
    fun createLandmarkSubComponent(): LandmarkSubComponent
}