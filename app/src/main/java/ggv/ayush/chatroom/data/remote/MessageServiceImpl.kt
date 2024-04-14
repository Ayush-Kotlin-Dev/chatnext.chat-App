package ggv.ayush.chatroom.data.remote

import ggv.ayush.chatroom.data.remote.dto.MessageDto
import ggv.ayush.chatroom.domain.model.Message
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class MessageServiceImpl(
    private val client : HttpClient
) : MessageService{
    override suspend fun getAllMessages(): List<Message> {
        return try{
            client.get<List<MessageDto>>(
                MessageService.EndPoint.GetAllMessages.url
            ).map { it.toMessage() }

        }catch (e: Exception){
            emptyList()
        }
    }
}