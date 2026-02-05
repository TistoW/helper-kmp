package com.tisto.smartvote

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform