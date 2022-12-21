package com.monstarlab.features.login.ui

import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.monstarlab.core.ui.ComposeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : ComposeFragment() {

    private val viewModel by viewModels<LoginViewModel>()

    override val content: @Composable () -> Unit = {
        val coordinator = rememberLoginCoordinator(viewModel, findNavController())
        LoginRoute(coordinator)
    }
}
