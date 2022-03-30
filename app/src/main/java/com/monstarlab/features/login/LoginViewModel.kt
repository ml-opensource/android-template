package com.monstarlab.features.login

import androidx.lifecycle.*
import com.monstarlab.arch.extensions.LoadingAware
import com.monstarlab.arch.extensions.ViewErrorAware
import com.monstarlab.arch.extensions.onSuccess
import com.monstarlab.core.usecases.user.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel(), ViewErrorAware, LoadingAware {

    val stateFlow = MutableStateFlow(LoginViewState())
    val loginResultFlow = MutableSharedFlow<Unit>()

    init {
        loadingFlow
            .onEach { stateFlow.emit(stateFlow.value.copy(isLoading = it)) }
            .launchIn(viewModelScope)
    }


    fun handle() {}


    fun login() {
        loginUseCase
            .login(stateFlow.value.email, stateFlow.value.password)
            .bindError(this)
            .bindLoading(this)
            .onSuccess { loginResultFlow.emit(Unit) }
            .launchIn(viewModelScope)
    }

    fun onPasswordTextChanged(text: String) {
        stateFlow.value = stateFlow.value.copy(password = text)
    }

    fun onEmailTextChanged(text: String) {
        stateFlow.value = stateFlow.value.copy(email = text)
    }
}
