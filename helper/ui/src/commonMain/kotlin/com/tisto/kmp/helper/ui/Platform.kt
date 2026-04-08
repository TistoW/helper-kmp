package com.tisto.kmp.helper.ui

interface PlatformUi {
    val name: String
    val type: String
}

expect fun getPlatformUi(): PlatformUi