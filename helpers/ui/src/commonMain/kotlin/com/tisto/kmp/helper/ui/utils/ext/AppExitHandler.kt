package com.tisto.kmp.helper.ui.utils.ext

import androidx.compose.runtime.Composable

expect fun isMobilePhone(): Boolean // for real device android

expect class AppExitHandler {
    fun exit()
}

@Composable
expect fun rememberAppExitHandler(): AppExitHandler