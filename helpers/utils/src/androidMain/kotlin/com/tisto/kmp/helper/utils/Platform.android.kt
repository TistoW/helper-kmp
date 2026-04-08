package com.tisto.kmp.helper.utils

import android.os.Build

class AndroidPlatform : PlatformUtils {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override val type: String = "android"
}

actual fun getPlatformUtils(): PlatformUtils = AndroidPlatform()