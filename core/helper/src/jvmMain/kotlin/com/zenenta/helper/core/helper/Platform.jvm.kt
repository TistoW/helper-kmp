package com.zenenta.helper.core.helper

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
    override val type: String = "windows"
}

actual fun getPlatform(): Platform = JVMPlatform()