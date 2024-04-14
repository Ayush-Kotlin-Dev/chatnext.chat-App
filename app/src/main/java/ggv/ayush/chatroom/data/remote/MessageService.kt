package ggv.ayush.chatroom.data.remote

import ggv.ayush.chatroom.data.remote.dto.MessageDto
import ggv.ayush.chatroom.domain.model.Message

interface MessageService {

    suspend fun getAllMessages(): List<Message>

    companion object {
        const val BASE_URL = "http://10.0.2.2:8080/"
    }
    sealed class EndPoint(val url: String) {
        object GetAllMessages : EndPoint("${BASE_URL}/messages")
    }
}