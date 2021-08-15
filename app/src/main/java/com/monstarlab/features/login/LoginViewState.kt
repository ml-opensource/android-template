package com.monstarlab.features.login

data class LoginViewState(
    val email: String = "eve.holt@reqres.in",
    val password: String = "cityslicka",
    val isLoading: Boolean = false
)
