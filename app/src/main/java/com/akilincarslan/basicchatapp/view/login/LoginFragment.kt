package com.akilincarslan.basicchatapp.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.akilincarslan.basicchatapp.R
import com.akilincarslan.basicchatapp.databinding.FragmentLoginBinding
import com.akilincarslan.basicchatapp.view.BindingFragment
import com.akilincarslan.basicchatapp.view.utils.navigateSafely
import kotlinx.coroutines.flow.collect

class LoginFragment : BindingFragment<FragmentLoginBinding>() {
    private val viewModel: LoginViewModel by viewModels()
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentLoginBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            viewModel.connectUser(binding.editUsername.text.toString())
            it.isEnabled = false
        }
        subscribeLoginState()
    }

    private fun subscribeLoginState() {
        lifecycleScope.launchWhenStarted {
            viewModel.loginState.collect { state ->
                when (state) {
                    is LoginViewModel.LoginState.LoginError -> {
                        binding.btnLogin.isEnabled = true
                    }
                    LoginViewModel.LoginState.Success -> {
                        findNavController().navigateSafely(R.id.action_loginFragment_to_mainFragment)
                    }
                }
            }
        }

    }
}