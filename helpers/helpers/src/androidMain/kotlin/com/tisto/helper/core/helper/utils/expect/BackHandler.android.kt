package com.tisto.helper.core.helper.utils.expect

import androidx.compose.runtime.Composable
import androidx.activity.compose.BackHandler

@Composable
internal actual fun PlatformBackDispatcherHost(
    dispatcher: com.tisto.kmp.helper.ui.utils.expect.BackDispatcher,
    content: @Composable () -> Unit
) {
    BackHandler(enabled = dispatcher.canGoBack) {
        dispatcher.handleBack()
    }
    content()
}

@Composable
actual fun BackHandler(enabled: Boolean, onBack: () -> Unit) {
    androidx.activity.compose.BackHandler(enabled, onBack)
}
