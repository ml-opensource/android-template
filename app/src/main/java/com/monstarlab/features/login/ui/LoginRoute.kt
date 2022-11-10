package com.monstarlab.features.login.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.monstarlab.core.ui.effects.SystemUISideEffect
import com.monstarlab.designsystem.theme.AppTheme

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun LoginRoute(
    coordinator: LoginCoordinator,
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle(LoginState())

    val useDarkIcons = !isSystemInDarkTheme()
    SystemUISideEffect { controller ->
        controller.setSystemBarsColor(Color.Transparent, useDarkIcons)
    }

    // UI Actions
    val actions = rememberLoginActions(coordinator)

    // UI Rendering
    AppTheme {
        LoginScreen(uiState, actions)
    }
}

@Composable
fun rememberLoginActions(coordinator: LoginCoordinator): LoginActions {
    return remember(coordinator) {
        LoginActions(
            onEmailChange = coordinator.viewModel::onEmailChange,
            onLoginClick = coordinator.viewModel::login,
            onPasswordChange = coordinator.viewModel::onPasswordChange
        )
    }
}
