package com.akilincarslan.basicchatapp.view.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.akilincarslan.basicchatapp.databinding.FragmentChatBinding
import com.akilincarslan.basicchatapp.view.BindingFragment
import com.getstream.sdk.chat.viewmodel.MessageInputViewModel
import com.getstream.sdk.chat.viewmodel.messages.MessageListViewModel
import io.getstream.chat.android.ui.message.input.viewmodel.bindView
import io.getstream.chat.android.ui.message.list.header.viewmodel.MessageListHeaderViewModel
import io.getstream.chat.android.ui.message.list.header.viewmodel.bindView
import io.getstream.chat.android.ui.message.list.viewmodel.bindView
import io.getstream.chat.android.ui.message.list.viewmodel.factory.MessageListViewModelFactory

class ChatFragment :BindingFragment<FragmentChatBinding>() {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentChatBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = MessageListViewModelFactory("channelID")
        val messageListHeaderViewModel :MessageListHeaderViewModel by viewModels {factory}
        val messageListViewModel :MessageListViewModel by viewModels {factory}
        val messageInputViewModel : MessageInputViewModel by viewModels{factory}

        messageListHeaderViewModel.bindView(binding.messageListHeaderView,viewLifecycleOwner)
        messageListViewModel.bindView(binding.messageListView,viewLifecycleOwner)
        messageInputViewModel.bindView(binding.messageInputView,viewLifecycleOwner)

        messageListViewModel.state.observe(viewLifecycleOwner) {state->
            if (state is MessageListViewModel.State.NavigateUp) {
                findNavController().navigateUp()
            }
        }
    }
}