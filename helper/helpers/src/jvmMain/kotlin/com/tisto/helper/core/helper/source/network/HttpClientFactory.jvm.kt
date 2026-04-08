package com.tisto.helper.core.helper.source.network

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.cio.CIO

actual fun getPlatformEngine(): HttpClientEngineFactory<*> = CIO