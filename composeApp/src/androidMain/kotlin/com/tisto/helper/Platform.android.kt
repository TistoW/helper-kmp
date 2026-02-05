package com.tisto.helper

import android.os.Build

class AndroidPlatform : Platform {
    override val name: String = "Ini di dalam Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()