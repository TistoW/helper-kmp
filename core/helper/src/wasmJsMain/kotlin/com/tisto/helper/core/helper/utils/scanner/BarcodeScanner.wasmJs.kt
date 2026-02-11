package com.tisto.helper.core.helper.utils.scanner

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal actual fun PlatformCameraScanner(
    modifier: Modifier,
    onResult: (BarcodeResult) -> Unit,
    onError: (Throwable) -> Unit
) {
}