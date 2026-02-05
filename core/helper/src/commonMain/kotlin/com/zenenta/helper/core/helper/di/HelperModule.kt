package com.zenenta.helper.core.helper.di

import com.zenenta.helper.core.helper.source.network.HttpClientFactory
import com.zenenta.helper.core.helper.utils.prefs.PrefManager
import org.koin.core.module.dsl.singleOf
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
