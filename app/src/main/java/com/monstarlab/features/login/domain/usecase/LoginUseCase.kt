package com.monstarlab.features.login.domain.usecase

import com.monstarlab.core.extensions.suspendRunCatching
import com.monstarlab.features.auth.domain.repository.AuthRepository
import com.monstarlab.features.user.domain.repository.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(email: String, password: String) = suspendRunCatching {
        authRepository.login(email, password)
        userRepository.get()
    }
}
