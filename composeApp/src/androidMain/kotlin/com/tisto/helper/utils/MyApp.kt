package com.tisto.helper.utils

import android.app.Application
import com.tisto.helper.core.helper.utils.prefs.PlatformPrefs

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        PlatformPrefs.init(this)
    }
}