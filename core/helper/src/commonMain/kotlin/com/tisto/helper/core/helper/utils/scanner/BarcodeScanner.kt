package com.tisto.helper.core.helper.utils.scanner


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tisto.helper.core.helper.ui.theme.Colors
import com.tisto.helper.core.helper.ui.theme.Spacing
import com.tisto.helper.core.helper.ui.theme.TextAppearance
import com.tisto.helper.core.helper.ui.theme.ZenentaHelperTheme
import com.tisto.helper.core.helper.utils.ext.TabletPreview
import com.tisto.helper.core.helper.utils.ext.logs

data class BarcodeResult(
    val raw: String,
    val format: String? = null,
)

@Composable
fun BarcodeScannerView(
    modifier: Modifier = Modifier,
    title: String = "Scan Barcode",
    onResult: (BarcodeResult) -> Unit
) {
    var errorText by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Colors.Black)
    ) {
        PlatformCameraScanner(
            modifier = Modifier.fillMaxSize(),
            onResult = onResult,
            onError = { errorText = it.message ?: "Camera error" }
        )

        // Top bar overlay
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.normal)
                .align(Alignment.TopCenter),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = TextAppearance.title2Bold(),
                color = Colors.White,
                modifier = Modifier.weight(1f)
            )
        }

        // Hint / error
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(Spacing.normal),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (errorText != null) {
                Surface(
                    color = Colors.Red,
                    tonalElevation = 2.dp,
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(
                        text = errorText!!,
                        modifier = Modifier.padding(12.dp),
                        color = Colors.White,
                        style = TextAppearance.body2()
                    )
                }
                Spacer(Modifier.height(Spacing.small))
            }

            Text(
                text = "Arahkan kamera ke barcode / QR",
                color = Colors.White,
                style = TextAppearance.body2()
            )
        }
    }
}

@TabletPreview
@Composable
fun AttendancePreview() {
    ZenentaHelperTheme {
        BarcodeScannerView(
            modifier = Modifier.fillMaxSize(),
            onResult = { res ->
                logs("BarcodeScannerView: ${res.raw}")
//                        onScanned(res.raw)
//                onBack()
            }
        )
    }
}


@Composable
internal expect fun PlatformCameraScanner(
    modifier: Modifier = Modifier,
    onResult: (BarcodeResult) -> Unit,
    onError: (Throwable) -> Unit,
)
