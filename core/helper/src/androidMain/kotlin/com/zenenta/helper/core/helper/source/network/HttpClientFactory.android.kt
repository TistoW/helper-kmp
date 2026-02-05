package com.zenenta.helper.core.helper.source.network

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp

actual fun getPlatformEngine(): HttpClientEngineFactory<*> = OkHttp
