package com.tisto.helper.core.helper.utils.ext

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

actual fun isMobilePhone() = true

actual class AppExitHandler(private val activity: Activity?) {
    actual fun exit() {
        activity?.finishAffinity()
    }
}

@Composable
actual fun rememberAppExitHandler(): com.tisto.kmp.helper.ui.utils.ext.AppExitHandler {
    val context = LocalContext.current
    val activity = context as? Activity
    return remember(activity) {
        _root_ide_package_.com.tisto.kmp.helper.ui.utils.ext.AppExitHandler(
            activity
        )
    }
}