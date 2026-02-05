package com.zenenta.helper.core.helper.source.network

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.js.Js

actual fun getPlatformEngine(): HttpClientEngineFactory<*> = Js