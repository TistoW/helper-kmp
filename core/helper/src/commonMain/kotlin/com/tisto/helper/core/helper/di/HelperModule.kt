package com.tisto.helper.core.helper.di

import com.tisto.helper.core.helper.utils.prefs.PrefManager
import org.koin.dsl.module

val helperModule = module {
    single { PrefManager }

    // API
//    singleOf(::SplashApi)

    // Repository (concrete class, no interface)
//    singleOf(::SplashRepository)

    // ViewModels
//    viewModelOf(::SplashViewModel)
}
