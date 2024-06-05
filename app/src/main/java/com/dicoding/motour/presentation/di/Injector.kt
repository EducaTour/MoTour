package com.dicoding.motour.presentation.di

import com.dicoding.motour.presentation.di.home.HomeSubComponent

interface Injector {
    fun createHomeSubComponent(): HomeSubComponent
}