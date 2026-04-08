package com.tisto.kmp.helper.utils

interface PlatformUtils {
    val name: String
    val type: String
}

expect fun getPlatformUtils(): PlatformUtils