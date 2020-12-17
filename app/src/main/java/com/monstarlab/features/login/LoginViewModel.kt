package com.monstarlab.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monstarlab.arch.extensions.onError
import com.monstarlab.arch.extensions.onSuccess
import com.monstarlab.core.sharedui.errorhandling.ViewError
import com.monstarlab.core.sharedui.errorhandling.mapToViewError
import com.monstarlab.core.usecases.user.LoginUseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class LoginViewModel @Inject constructor(
        private val loginUseCase: LoginUseCase
): ViewModel() {

    val loadingFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val errorFlow: MutableSharedFlow<ViewError> = MutableSharedFlow()
    val loginResultFlow: MutableSharedFlow<Boolean> = MutableSharedFlow()

    fun login(email: String, password: String) {
        loginUseCase
                .login(email, password)
                .onStart {
                    loadingFlow.value = true
                }.onSuccess {
                    loginResultFlow.emit(true)
                }.onError {
                    errorFlow.emit(it.mapToViewError())
                }.onCompletion {
                    loadingFlow.value = false
                }.launchIn(viewModelScope)
    }

}