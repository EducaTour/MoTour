package com.dicoding.motour.presentation.di.startup

import com.dicoding.motour.presentation.MainActivity
import dagger.Subcomponent

@StartupScope
@Subcomponent(modules = [StartupModule::class])
interface StartupSubComponent {
    fun inject(mainActivity: MainActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): StartupSubComponent
    }
}