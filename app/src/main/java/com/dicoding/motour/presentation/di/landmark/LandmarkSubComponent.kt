package com.dicoding.motour.presentation.di.landmark

import com.dicoding.motour.presentation.landmark.LandmarkDetailActivity
import dagger.Subcomponent

@LandmarkScope
@Subcomponent(modules = [LandmarkModule::class])
interface LandmarkSubComponent {
    fun inject(activity: LandmarkDetailActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): LandmarkSubComponent
    }
}