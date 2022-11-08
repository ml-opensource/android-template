package com.monstarlab.features.login.ui

/**
 * UI State that represents LoginScreen
 **/
data class LoginState(
    val email: String = "eve.holt@reqres.in",
    val password: String = "cityslicka",
    val isLoading: Boolean = false,
    val error: String? = null,
    val loginButtonEnabled: Boolean = false,
    val isLoggedIn: Boolean = false
)

/**
 * Login Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class LoginActions(
    val onPasswordChange: (String) -> Unit = {},
    val onEmailChange: (String) -> Unit = {},
    val onLoginClick: () -> Unit = {}
)
