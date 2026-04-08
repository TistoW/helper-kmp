package com.tisto.kmp.helper.network

class WasmPlatform : PlatformNetwork {
    override val name: String = "Web with Kotlin/Wasm"
    override val type: String = "wasm"
}

actual fun getPlatformNetwork(): PlatformNetwork = WasmPlatform()