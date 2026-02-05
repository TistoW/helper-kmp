package com.tisto.smartvote.di

import com.tisto.helper.core.helper.di.helperModule
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
