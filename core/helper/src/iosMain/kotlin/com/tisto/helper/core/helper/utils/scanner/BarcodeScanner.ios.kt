package com.tisto.helper.core.helper.utils.scanner

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFoundation.*
import platform.CoreGraphics.CGRectMake
import platform.UIKit.UIView
import platform.darwin.NSObject
import platform.darwin.dispatch_get_main_queue

@OptIn(ExperimentalForeignApi::class)
@Composable
internal actual fun PlatformCameraScanner(
    modifier: Modifier,
    onResult: (BarcodeResult) -> Unit,
    onError: (Throwable) -> Unit,
) {
    val controller = remember { IOSScannerController(onResult, onError) }

    DisposableEffect(Unit) {
        onDispose { controller.stop() }
    }

    UIKitView(
        modifier = modifier,
        factory = {
            controller.makeView()
        },
        update = {
            controller.start()
        }
    )
}

private class IOSScannerController(
    private val onResult: (BarcodeResult) -> Unit,
    private val onError: (Throwable) -> Unit,
) : NSObject(), AVCaptureMetadataOutputObjectsDelegateProtocol {

    private val session = AVCaptureSession()
    private val output = AVCaptureMetadataOutput()
    private var previewLayer: AVCaptureVideoPreviewLayer? = null
    private var container: UIView? = null
    private var started = false

    @OptIn(ExperimentalForeignApi::class)
    fun makeView(): UIView {
        val view = UIView(frame = CGRectMake(0.0, 0.0, 0.0, 0.0))
        container = view

        val layer = AVCaptureVideoPreviewLayer(session = session)
        layer.videoGravity = AVLayerVideoGravityResizeAspectFill
        previewLayer = layer
        view.layer.addSublayer(layer)

        return view
    }

    @OptIn(ExperimentalForeignApi::class)
    fun start() {
        if (started) return
        started = true

        // Resize preview layer to view bounds
        container?.let { v ->
            previewLayer?.frame = v.bounds
        }

        val status = AVCaptureDevice.authorizationStatusForMediaType(AVMediaTypeVideo)
        if (status == AVAuthorizationStatusNotDetermined) {
            AVCaptureDevice.requestAccessForMediaType(AVMediaTypeVideo) { granted ->
                if (granted) setupSessionAndRun()
            }
        } else if (status == AVAuthorizationStatusAuthorized) {
            setupSessionAndRun()
        } else {
            onError(IllegalStateException("Izin kamera ditolak di iOS"))
        }
    }

    fun stop() {
        runCatching {
            if (session.running) session.stopRunning()
        }
        started = false
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun setupSessionAndRun() {
        runCatching {
            val device = AVCaptureDevice.defaultDeviceWithMediaType(AVMediaTypeVideo)
                ?: error("No camera device")

            val input = AVCaptureDeviceInput.deviceInputWithDevice(device, error = null) as AVCaptureDeviceInput
            session.beginConfiguration()

            if (session.canAddInput(input)) session.addInput(input)
            if (session.canAddOutput(output)) session.addOutput(output)

            output.setMetadataObjectsDelegate(this, dispatch_get_main_queue())

            // Formats (QR + common barcodes)
            output.metadataObjectTypes = listOf(
                AVMetadataObjectTypeQRCode,
                AVMetadataObjectTypeEAN13Code,
                AVMetadataObjectTypeEAN8Code,
                AVMetadataObjectTypeCode128Code,
                AVMetadataObjectTypeUPCECode,
                AVMetadataObjectTypeAztecCode,
                AVMetadataObjectTypePDF417Code
            )

            session.commitConfiguration()
            session.startRunning()
        }.onFailure { onError(it) }
    }

    override fun captureOutput(
        output: AVCaptureOutput,
        didOutputMetadataObjects: List<*>,
        fromConnection: AVCaptureConnection
    ) {
        val first = didOutputMetadataObjects.firstOrNull() as? AVMetadataMachineReadableCodeObject
        val raw = first?.stringValue
        if (!raw.isNullOrEmpty()) {
            onResult(BarcodeResult(raw = raw, format = first.type))
        }
    }
}
