package com.monstarlab.features.login

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewErrorFlow
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import com.monstarlab.R
import com.monstarlab.arch.extensions.collectFlow
import com.monstarlab.arch.extensions.snackErrorFlow
import com.monstarlab.core.sharedui.ComposeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : ComposeFragment() {

    private val viewModel by viewModels<LoginViewModel>()

    override val content: @Composable () -> Unit = {
        val state = viewModel.stateFlow.collectAsState().value
        LoginScreen(state) { event ->
            when(event) {
                LoginScreenEvent.LoginButtonPress -> viewModel.login()
                is LoginScreenEvent.LoginChange -> viewModel.onEmailTextChanged(event.value)
                is LoginScreenEvent.PasswordChange -> viewModel.onPasswordTextChanged(event.value)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        collectFlow(viewModel.loginResultFlow) {
            findNavController().navigate(R.id.resourceFragment)
        }

        snackErrorFlow(viewModel.viewErrorFlow, view)
    }
}
