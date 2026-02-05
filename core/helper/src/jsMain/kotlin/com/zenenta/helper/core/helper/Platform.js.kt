package com.zenenta.helper.core.helper

class JsPlatform : Platform {
    override val name: String = "WebJs with Kotlin/JS"
    override val type: String = "webJs"
}

actual fun getPlatform(): Platform = JsPlatform()