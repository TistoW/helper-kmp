package com.tisto.helper.core.helper.utils.scanner

@androidx.compose.runtime.Composable
internal actual fun PlatformCameraScanner(
    modifier: androidx.compose.ui.Modifier,
    onResult: (com.tisto.helper.core.helper.utils.scanner.BarcodeResult) -> Unit,
    onError: (Throwable) -> Unit
) {
}