package com.monstarlab.features.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.loadingFlow
import androidx.lifecycle.viewmodel.compose.viewModel
import com.monstarlab.core.sharedui.components.AppButton


@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    val state by viewModel.stateFlow.collectAsState()
    val isLoading by viewModel.loadingFlow.collectAsState()
    Scaffold {
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