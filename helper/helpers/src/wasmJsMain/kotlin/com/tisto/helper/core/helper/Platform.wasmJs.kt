package com.tisto.helper.core.helper


class WasmPlatform : Platform {
    override val name: String = "Web with Kotlin/Wasm"
    override val type: String = "wasm"
}

actual fun getPlatform(): Platform = WasmPlatform()