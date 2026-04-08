package com.tisto.kmp.helper.ui

import android.os.Build

class AndroidPlatform : PlatformUi {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override val type: String = "android"
}

actual fun getPlatformUi(): PlatformUi = AndroidPlatform()