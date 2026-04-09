package com.tisto.kmp.helper.utils

interface Platform {
    val name: String
    val type: String
    val platform: String
}

expect fun getPlatform(): Platform