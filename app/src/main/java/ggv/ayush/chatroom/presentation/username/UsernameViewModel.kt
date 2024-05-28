package ggv.ayush.chatroom.presentation.username

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ggv.ayush.chatroom.data.remote.ChatSocketService
import ggv.ayush.chatroom.data.remote.MessageService
import ggv.ayush.chatroom.domain.model.Message
import ggv.ayush.chatroom.util.Resource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsernameViewModel @Inject constructor(
    private val messageService: MessageService,
    private val chatSocketService: ChatSocketService
) : ViewModel() {

    private val _messages = MutableLiveData<Resource<List<Message>>>()
    val messages: LiveData<Resource<List<Message>>> = _messages

    private val _message = MutableLiveData<Resource<Message>>()
    val message: LiveData<Resource<Message>> = _message

    //main
    private val _userName = mutableStateOf("")
    val userNameText: State<String> = _userName

    private val _onJoinChat = MutableSharedFlow<String>()
    val onJoinChat = _onJoinChat.asSharedFlow()

    fun onUsernameChange(username: String) {
        _userName.value = username
    }

    fun onJoinChatClick() {
        viewModelScope.launch {
            if(_userName.value.isNotBlank()) {
                _onJoinChat.emit(_userName.value)
            }
        }
        }

        //main

        private val _isTyping = MutableLiveData<Boolean>()
        val isTyping: LiveData<Boolean> = _isTyping

        private val _isConnected = MutableLiveData<Boolean>()
        val isConnected: LiveData<Boolean> = _isConnected

        private val _isConnecting = MutableLiveData<Boolean>()
        val isConnecting: LiveData<Boolean> = _isConnecting

        private val _isError = MutableLiveData<Boolean>()
        val isError: LiveData<Boolean> = _isError

        private val _isErrorConnecting = MutableLiveData<Boolean>()
        val isErrorConnecting: LiveData<Boolean> = _isErrorConnecting

        private val _isErrorTyping = MutableLiveData<Boolean>()
        val isErrorTyping: LiveData<Boolean> = _isErrorTyping

        private val _isErrorSending = MutableLiveData<Boolean>()
        val isErrorSending: LiveData<Boolean> = _isErrorSending

        private val _isErrorReceiving = MutableLiveData<Boolean>()
        val isErrorReceiving: LiveData<Boolean> = _isErrorReceiving

        private val _isErrorDisconnecting = MutableLiveData<Boolean>()
        val isErrorDisconnecting: LiveData<Boolean> = _isErrorDisconnecting

        private val _isErrorConnectingSocket = MutableLiveData<Boolean>()
        val isErrorConnectingSocket: LiveData<Boolean> = _isErrorConnectingSocket

        private val _isErrorDisconnectingSocket = MutableLiveData<Boolean>()
        val isErrorDisconnectingSocket: LiveData<Boolean> = _isErrorDisconnectingSocket

        private val _isErrorSendingSocket = MutableLiveData<Boolean>()
        val isErrorSendingSocket: LiveData<Boolean> = _isErrorSendingSocket

        private val _isErrorReceivingSocket = MutableLiveData<Boolean>()
        val isErrorReceivingSocket: LiveData<Boolean> = _isErrorReceivingSocket

        private val _isErrorTypingSocket = MutableLiveData<Boolean>()
        val isErrorTypingSocket: LiveData<Boolean> = _isErrorTypingSocket

        private val _isErrorConnectingSocketService = MutableLiveData<Boolean>()
        val isErrorConnectingSocketService: LiveData<Boolean> = _isErrorConnectingSocketService
    }
