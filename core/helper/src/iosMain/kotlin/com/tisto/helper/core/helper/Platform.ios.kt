package com.tisto.helper.core.helper

import platform.UIKit.UIDevice

class IosPlatform : Platform {
    override val name: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    override val type: String = "ios"
}

actual fun getPlatform(): Platform = IosPlatform()