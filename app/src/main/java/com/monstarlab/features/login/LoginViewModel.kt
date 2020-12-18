package com.monstarlab.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.bindError
import androidx.lifecycle.bindLoading
import androidx.lifecycle.viewModelScope
import com.monstarlab.arch.extensions.onSuccess
import com.monstarlab.core.usecases.user.LoginUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

class LoginViewModel @Inject constructor(
        private val loginUseCase: LoginUseCase
) : ViewModel() {
    val loginResultFlow: MutableSharedFlow<Boolean> = MutableSharedFlow()

    fun login(email: String, password: String) {
        loginUseCase
                .login(email, password)
                .bindLoading(this)
                .bindError(this)
                .onSuccess {
                    loginResultFlow.emit(true)
                }
                .launchIn(viewModelScope)
    }
}