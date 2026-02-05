package com.zenenta.smartvote

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform