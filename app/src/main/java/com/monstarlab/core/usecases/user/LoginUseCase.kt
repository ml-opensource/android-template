package com.monstarlab.core.usecases.user

import com.monstarlab.arch.extensions.*
import com.monstarlab.core.data.repositories.UserRepository
import com.monstarlab.core.domain.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
        private val userRepository: UserRepository
) {

    fun login(email: String, password: String): Flow<UseCaseResult<User>> = safeFlow {
        userRepository.login(email, password)
        userRepository.getUser()
    }

}