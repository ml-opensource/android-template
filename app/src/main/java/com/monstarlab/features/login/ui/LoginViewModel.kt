package com.monstarlab.features.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monstarlab.core.error.toError
import com.monstarlab.features.login.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())

    val stateFlow: StateFlow<LoginState> = _stateFlow.asStateFlow()

    fun onEmailChange(value: String) {
        _stateFlow.update { state ->
            state.copy(email = value, error = null)
        }
    }

    fun onPasswordChange(value: String) {
        _stateFlow.update { state ->
            state.copy(password = value, error = null)
        }
    }

    fun login() {
        viewModelScope.launch {
            _stateFlow.update { it.copy(isLoading = true) }
            val state = _stateFlow.value
            val result = loginUseCase(state.email, state.password)
            _stateFlow.update { state ->
                state.copy(
                    error = result.exceptionOrNull()?.toError(),
                    isLoading = false,
                    isLoggedIn = result.isSuccess
                )
            }
        }
    }
}
