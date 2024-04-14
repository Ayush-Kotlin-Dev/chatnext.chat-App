package ggv.ayush.chatroom.data.remote

import ggv.ayush.chatroom.data.remote.dto.MessageDto
import ggv.ayush.chatroom.domain.model.Message
import ggv.ayush.chatroom.util.Resource
import io.ktor.client.HttpClient
import io.ktor.client.features.websocket.webSocketSession
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.WebSocketSession
import io.ktor.http.cio.websocket.close
import io.ktor.http.cio.websocket.readText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.isActive
import kotlinx.serialization.json.Json

class ChatSocketServiceImp(
    private val client: HttpClient
) : ChatSocketService {

    private var socket: WebSocketSession? = null


    override suspend fun initSession(userName: String): Resource<Unit> {
        return try {
            socket = client.webSocketSession {
                url(ChatSocketService.EndPoint.ChatSocketRoute.url)
            }
            if (socket?.isActive == true) {
                Resource.Success(Unit)
            } else {
                Resource.Error("Failed to establish connection")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.localizedMessage ?: "An unexpected error occurred")
        }
    }

    override suspend fun sendMessage(message: String) {
        try {
            socket?.send(Frame.Text(message))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    override fun observeMessages(): Flow<Message> {
        return try {
            socket?.incoming
                ?.receiveAsFlow()
                ?.filter { it is Frame.Text }
                ?.map {
                val json = (it as? Frame.Text)?.readText() ?: ""
                val messageDto = Json.decodeFromString<MessageDto>(json)
                messageDto.toMessage()
            }?: emptyFlow()
        }catch (e: Exception) {
            emptyFlow()
        }
    }

    override suspend fun closeSession() {
        try {
            socket?.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}