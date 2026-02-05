package com.zenenta.helper.core.helper.utils.expect

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.*

@Composable
actual fun BackHandler(enabled: Boolean, onBack: () -> Unit) {
}

@Composable
internal actual fun PlatformBackDispatcherHost(
    dispatcher: BackDispatcher,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .onPreviewKeyEvent { e ->
                if (!dispatcher.canGoBack) return@onPreviewKeyEvent false
                if (e.type != KeyEventType.KeyUp) return@onPreviewKeyEvent false

                val isBack =
                    e.key == Key.Escape ||
                            e.key == Key.Backspace // NOTE: may conflict with text input

                if (isBack) {
                    dispatcher.handleBack()
                    true
                } else false
            }
    ) {
        content()
    }
}