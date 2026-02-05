package com.zenenta.helper.core.helper

interface Platform {
    val name: String
    val type: String
}

expect fun getPlatform(): Platform