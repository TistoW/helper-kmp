package com.tisto.kmp.helper.newtork


class JVMPlatform: PlatformNetwork {
    override val name: String = "Java ${System.getProperty("java.version")}"
    override val type: String = "windows"
}

actual fun getPlatformNetwork(): PlatformNetwork = JVMPlatform()