package ggv.ayush.chatroom.presentation.chat

import ggv.ayush.chatroom.domain.model.Message

data class ChatState(
    val messages: List<Message> = emptyList(),
    val isLoading: Boolean = false
)
