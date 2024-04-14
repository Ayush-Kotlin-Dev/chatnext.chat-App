package ggv.ayush.chatroom.data.remote

import ggv.ayush.chatroom.domain.model.Message
import ggv.ayush.chatroom.util.Resource
import kotlinx.coroutines.flow.Flow

interface ChatSocketService {

    suspend fun initSession(
        userName: String,
    ): Resource<Unit>

    suspend fun sendMessage(
        message: String
    )

    fun observeMessages(): Flow<Message>

    suspend fun closeSession()

    companion object {
        const val BASE_URL = "ws://10.0.2.2:8080/"
    }
    sealed class EndPoint(val url: String) {
        object ChatSocketRoute : EndPoint("${BASE_URL}/chat-socket")
    }
}