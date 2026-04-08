package com.tisto.kmp.helper.network

class JsPlatform : PlatformNetwork {
    override val name: String = "WebJs with Kotlin/JS"
    override val type: String = "webJs"
}

actual fun getPlatformNetwork(): PlatformNetwork = JsPlatform()