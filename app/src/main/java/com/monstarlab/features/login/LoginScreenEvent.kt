package com.monstarlab.features.login

sealed interface LoginScreenEvent {
    data class LoginChange(val value: String) : LoginScreenEvent
    data class PasswordChange(val value: String) : LoginScreenEvent
    object LoginButtonPress: LoginScreenEvent
}