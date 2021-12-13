package com.akilincarslan.basicchatapp.view

import android.app.Application
import com.akilincarslan.basicchatapp.apiKey
import com.akilincarslan.basicchatapp.token
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.call.enqueue
import io.getstream.chat.android.client.logger.ChatLogLevel
import io.getstream.chat.android.client.models.User
import io.getstream.chat.android.livedata.ChatDomain
import timber.log.Timber

class ChatApp :Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant()

        val chatClient = ChatClient.Builder(apiKey,applicationContext)
            .logLevel(ChatLogLevel.ALL)
            .build()

        // ChatDomain is used to have offline support or use UI Components package
        val chatDomain = ChatDomain.Builder(applicationContext,chatClient)
            .offlineEnabled()
            .userPresenceEnabled()
            .build()




    }
}