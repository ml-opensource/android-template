package com.monstarlab.features.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.monstarlab.R
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun LoginCoordinator(
    viewModel: LoginViewModel,
    navController: NavController
) {

    val state = viewModel.stateFlow.collectAsState().value

    LaunchedEffect(key1 = Unit) {
        viewModel.loginResultFlow
            .onEach { navController.navigate(R.id.resourceFragment) }
            .launchIn(this)
    }

    LoginScreen(
        state = state,
        onPasswordChange = viewModel::onPasswordTextChanged,
        onEmailChange = viewModel::onEmailTextChanged,
        onLoginClick = viewModel::login
    )
}