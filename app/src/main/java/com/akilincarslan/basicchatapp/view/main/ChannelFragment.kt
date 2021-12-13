package com.akilincarslan.basicchatapp.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.akilincarslan.basicchatapp.databinding.FragmentChannelBinding
import com.akilincarslan.basicchatapp.view.BindingFragment
import io.getstream.chat.android.client.models.Filters
import io.getstream.chat.android.ui.channel.list.header.viewmodel.ChannelListHeaderViewModel
import io.getstream.chat.android.ui.channel.list.header.viewmodel.bindView
import io.getstream.chat.android.ui.channel.list.viewmodel.ChannelListViewModel
import io.getstream.chat.android.ui.channel.list.viewmodel.bindView
import io.getstream.chat.android.ui.channel.list.viewmodel.factory.ChannelListViewModelFactory

class ChannelFragment :BindingFragment<FragmentChannelBinding>() {
    private val viewModel:ChannelViewModel by activityViewModels()
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentChannelBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user =viewModel.getUser()
        if (user == null) {
            findNavController().popBackStack()
            return
        }
        val factory = ChannelListViewModelFactory(
            filter = Filters.and(
                Filters.eq("type","messaging")
            ),
            sort = ChannelListViewModel.DEFAULT_SORT,
            limit = 15
        )
        val channelListViewModel : ChannelListViewModel by viewModels { factory }
        val channelListHeaderViewModel : ChannelListHeaderViewModel by viewModels()

        channelListViewModel.bindView(binding.channelListView,viewLifecycleOwner)
        channelListHeaderViewModel.bindView(binding.channelListHeaderView,viewLifecycleOwner)
        binding.channelListHeaderView.setOnUserAvatarClickListener {
            viewModel.logOut()
            findNavController().popBackStack()
        }

    }
}