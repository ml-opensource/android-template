package com.monstarlab.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monstarlab.arch.extensions.onResult
import com.monstarlab.core.domain.error.ErrorModel
import com.monstarlab.core.sharedui.errorhandling.ViewError
import com.monstarlab.core.sharedui.errorhandling.mapToViewError
import com.monstarlab.core.usecases.user.LoginUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    val loadingFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val errorFlow: MutableSharedFlow<ViewError> = MutableSharedFlow()
    val loginResultFlow: MutableSharedFlow<Boolean> = MutableSharedFlow()

    fun login(email: String, password: String) {
        loginUseCase
            .login(email, password)
            .onStart {
                loadingFlow.value = true
            }.onResult(onSuccess = {
                loginResultFlow.emit(true)
            }, onFailure = {
                if (it is ErrorModel) {
                    errorFlow.emit(it.mapToViewError())
                }
            })
            .onCompletion {
                loadingFlow.value = false
            }.launchIn(viewModelScope)
    }
}