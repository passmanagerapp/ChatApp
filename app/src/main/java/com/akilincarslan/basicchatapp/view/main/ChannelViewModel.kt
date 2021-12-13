package com.akilincarslan.basicchatapp.view.main

import androidx.lifecycle.ViewModel
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.User

class ChannelViewModel :ViewModel() {
    val client = ChatClient.instance()
    fun logOut() {
        client.disconnect()
    }
    fun getUser():User? {
        return client.getCurrentUser()
    }
}