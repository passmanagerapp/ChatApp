package com.akilincarslan.basicchatapp.view.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akilincarslan.basicchatapp.view.ChatApp
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.call.await
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel :ViewModel() {
    private val _loginState = MutableSharedFlow<LoginState>()
    val loginState = _loginState.asSharedFlow()
    fun connectUser(userName:String) {
        val client = ChatClient.instance()
        viewModelScope.launch {
           val result = client.connectGuestUser(
                userName,
                userName
            ).await()
            if (result.isSuccess) {
                _loginState.emit(LoginState.Success)
            } else if (result.isError) {
                _loginState.emit(LoginState.LoginError(result.error().message ?: "Unknown Error"))
            }
        }
    }
    sealed class LoginState() {
        data class LoginError(val message:String):LoginState()
        object Success :LoginState()
    }
}