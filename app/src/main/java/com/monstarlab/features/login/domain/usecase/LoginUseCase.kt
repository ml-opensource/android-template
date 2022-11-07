package com.monstarlab.features.login.domain.usecase

import com.monstarlab.features.auth.domain.AuthRepository
import com.monstarlab.features.user.domain.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) {

    suspend fun login(email: String, password: String) = runCatching {
        authRepository.login(email, password)
        userRepository.getUser()
    }
}
