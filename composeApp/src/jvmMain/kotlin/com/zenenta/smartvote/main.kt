package com.zenenta.smartvote

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.github.vinceglb.filekit.FileKit

fun main() = application {

    FileKit.init(appId = "com.zenenta.smartvote") // ganti sesuai appId kamu

    Window(
        onCloseRequest = ::exitApplication,
        title = "Smartvote",
        state = androidx.compose.ui.window.WindowState(
            width = 1280.dp,
            height = 720.dp
        )
    ) {
        App()
    }
}