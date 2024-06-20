package com.dicoding.motour.presentation.di.scanner

import com.dicoding.motour.presentation.scanner.PostScanActivity
import dagger.Subcomponent

@ScannerScope
@Subcomponent(modules = [ScannerModule::class])
interface ScannerSubComponent {
    fun inject(postScanActivity: PostScanActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): ScannerSubComponent
    }
}
