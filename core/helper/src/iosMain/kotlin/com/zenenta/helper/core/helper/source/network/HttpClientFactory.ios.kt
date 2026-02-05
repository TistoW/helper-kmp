package com.zenenta.helper.core.helper.source.network

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin

actual fun getPlatformEngine(): HttpClientEngineFactory<*> = Darwin