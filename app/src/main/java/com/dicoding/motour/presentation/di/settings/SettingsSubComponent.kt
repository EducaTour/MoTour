package com.dicoding.motour.presentation.di.settings

import com.dicoding.motour.presentation.setting.SettingsFragment
import dagger.Subcomponent

@SettingsScope
@Subcomponent(modules = [SettingsModule::class])
interface SettingsSubComponent {
    fun inject(settingsFragment: SettingsFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): SettingsSubComponent
    }
}