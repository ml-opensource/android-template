package com.monstarlab.features.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.loadingFlow
import androidx.lifecycle.viewErrorFlow
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import com.monstarlab.R
import com.monstarlab.arch.extensions.collectFlow
import com.monstarlab.arch.extensions.snackErrorFlow
import com.monstarlab.core.sharedui.ComposeFragment
import com.monstarlab.core.sharedui.components.AppButton
import com.monstarlab.core.sharedui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : ComposeFragment() {

    private val viewModel by viewModels<LoginViewModel>()

    override val content: @Composable () -> Unit = {
        LoginScreen(viewModel = viewModel)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        collectFlow(viewModel.loginResultFlow) {
            findNavController().navigate(R.id.resourceFragment)
        }

        snackErrorFlow(viewModel.viewErrorFlow, view)
    }
}

