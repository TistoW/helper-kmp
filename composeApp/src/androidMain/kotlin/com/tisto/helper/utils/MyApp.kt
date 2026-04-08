package com.tisto.helper.utils

import android.app.Application
import com.tisto.kmp.helper.ui.utils.prefs.PlatformPrefs

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        PlatformPrefs.init(this)
    }
}