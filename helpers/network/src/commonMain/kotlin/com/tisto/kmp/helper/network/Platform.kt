package com.tisto.kmp.helper.network

interface PlatformNetwork {
    val name: String
    val type: String
}

expect fun getPlatformNetwork(): PlatformNetwork