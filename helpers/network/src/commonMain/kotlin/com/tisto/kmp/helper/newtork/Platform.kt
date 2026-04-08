package com.tisto.kmp.helper.newtork

interface PlatformNetwork {
    val name: String
    val type: String
}

expect fun getPlatformNetwork(): PlatformNetwork