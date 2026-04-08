package com.tisto.kmp.helper.newtork

import platform.UIKit.UIDevice

class IosPlatform : PlatformNetwork {
    override val name: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    override val type: String = "ios"
}

actual fun getPlatformNetwork(): PlatformNetwork = IosPlatform()