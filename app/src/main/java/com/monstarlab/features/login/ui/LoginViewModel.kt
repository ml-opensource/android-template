package com.monstarlab.features.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monstarlab.core.extensions.LoadingAware
import com.monstarlab.core.extensions.ViewErrorAware
import com.monstarlab.features.login.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel(), ViewErrorAware, LoadingAware {

    val loginResultFlow: MutableSharedFlow<Boolean> = MutableSharedFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val result = loginUseCase.login(email, password)
            if (result.isSuccess) {
                loginResultFlow.emit(true)
            }
        }
    }
}
