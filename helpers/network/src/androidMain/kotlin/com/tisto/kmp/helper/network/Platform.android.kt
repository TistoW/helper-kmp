package com.tisto.kmp.helper.network

import android.os.Build

class AndroidPlatform : PlatformNetwork {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override val type: String = "android"
}

actual fun getPlatformNetwork(): PlatformNetwork = AndroidPlatform()