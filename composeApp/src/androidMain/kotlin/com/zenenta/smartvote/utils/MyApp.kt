package com.zenenta.smartvote.utils

import android.app.Application
import com.zenenta.helper.core.helper.utils.prefs.PlatformPrefs

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        PlatformPrefs.init(this)
    }
}