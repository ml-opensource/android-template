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
import com.monstarlab.core.sharedui.components.AppButton
import com.monstarlab.core.sharedui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = ComposeView(requireContext()).apply {
            setContent {
                AppTheme {
                    LoginScreen(viewModel = viewModel)
                }
            }
        }

        collectFlow(viewModel.loginResultFlow) {
            findNavController().navigate(R.id.resourceFragment)
        }

        snackErrorFlow(viewModel.viewErrorFlow, view)

        return view
    }
}

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    val state by viewModel.stateFlow.collectAsState()
    val isLoading by viewModel.loadingFlow.collectAsState()
    Scaffold() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = state.email,
                enabled = !isLoading,
                onValueChange = viewModel::onEmailTextChanged
            )
            Spacer(modifier = Modifier.padding(vertical = 4.dp))

            TextField(
                value = state.password,
                enabled = !isLoading,
                onValueChange = viewModel::onPasswordTextChanged,
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            AppButton(text = "Login") {
                viewModel.login()
            }
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            if (isLoading) {
                CircularProgressIndicator()
            }
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen(viewModel = viewModel())
}
