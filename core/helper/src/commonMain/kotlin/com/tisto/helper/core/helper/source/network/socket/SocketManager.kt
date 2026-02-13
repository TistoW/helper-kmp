package com.tisto.helper.core.helper.source.network.socket

import com.tisto.helper.core.helper.source.network.getPlatformEngine
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.url
import io.ktor.serialization.kotlinx.json.json
import io.ktor.websocket.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement


fun createHttpClient(): HttpClient {
    return HttpClient(getPlatformEngine()) {

        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }

        install(WebSockets)

        expectSuccess = false
    }
}


@Serializable
data class SocketEvent(
    val event: String,
    val data: JsonElement? = null
)

class SocketManager(
    private val url: String,
    private val scope: CoroutineScope
) {

    private val client = createHttpClient()
    var session: DefaultClientWebSocketSession? = null

    private val _events = MutableSharedFlow<SocketEvent>(
        extraBufferCapacity = 64,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val events = _events.asSharedFlow()
    private var listenJob: Job? = null
    suspend fun connect() {
        try {
            println("🟢 [Socket] Connecting to $url")

            session = client.webSocketSession {
                url(this@SocketManager.url)
            }

            println("✅ [Socket] Connected to $url")

            // 🔥 run listening in background
            listenJob = scope.launch {
                listen()
            }

        } catch (e: Exception) {
            println("❌ [Socket] Connection failed: ${e.message}")
        }
    }

    fun listen(eventName: String): Flow<SocketEvent> {
        return events.filter { it.event == eventName }
    }

    private suspend fun listen() {
        try {
            println("👂 [Socket] Start listening...")

            session?.incoming?.consumeEach { frame ->
                when (frame) {
                    is Frame.Text -> {
                        val text = frame.readText()
                        println("📩 [Socket] Received raw: $text")

                        try {
                            val event = Json.decodeFromString<SocketEvent>(text)

                            println("📨 [Socket] Event: ${event.event}")

                            _events.emit(event)
                        } catch (e: Exception) {
                            println("⚠️ [Socket] Invalid JSON: $text")
                        }
                    }

                    is Frame.Close -> {
                        println("🔴 [Socket] Server closed connection")
                        disconnect()
                    }

                    else -> {
                        println("ℹ️ [Socket] Other frame: $frame")
                    }
                }
            }
        } catch (e: Exception) {
            println("❌ [Socket] Listening error: ${e.message}")
        } finally {
            println("🔴 [Socket] Stopped listening")
        }
    }

    suspend fun emit(event: String) {
        try {
            val payload = SocketEvent(event = event)

            val json = Json.encodeToString(payload)

            println("📤 [Socket] Emit: $json")

            session?.send(Frame.Text(json))

        } catch (e: Exception) {
            println("❌ [Socket] Emit failed: ${e.message}")
        }
    }

    suspend fun disconnect() {
        try {
            println("🔴 [Socket] Disconnecting...")

            listenJob?.cancel()
            listenJob = null

            session?.close()
            session = null

            println("✅ [Socket] Disconnected")

        } catch (e: Exception) {
            println("⚠️ [Socket] Disconnect error: ${e.message}")
        }
    }

}

