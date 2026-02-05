package com.zenenta.helper.core.helper

import android.os.Build

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override val type: String = "android"
}

actual fun getPlatform(): Platform = AndroidPlatform()