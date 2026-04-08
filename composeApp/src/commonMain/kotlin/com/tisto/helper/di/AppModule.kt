package com.tisto.helper.di

import com.tisto.kmp.helper.ui.di.helperModule
import org.koin.dsl.module

val appModule = module {
//    viewModelOf(::SplashViewModel)
//    viewModelOf(::MainViewModel)
}

/**
 * Get all application modules
 */
fun getAppModules() = listOf(
    appModule,
    helperModule,
//    dataModule,
//    authModule,
//    ExampleModule,
//    ProductModule,
//    PosModule
)
