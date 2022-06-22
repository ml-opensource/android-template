package com.monstarlab.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.collectFlow
import com.monstarlab.arch.extensions.LoadingAware
import com.monstarlab.arch.extensions.ViewErrorAware
import com.monstarlab.core.usecases.user.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel(), ViewErrorAware, LoadingAware {

    val loginResultFlow: MutableSharedFlow<Boolean> = MutableSharedFlow()

    fun login(email: String, password: String) {
        collectFlow(loginUseCase.login(email, password)) {
            loginResultFlow.emit(true)
        }
    }
}
