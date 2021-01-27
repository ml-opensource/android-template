package com.monstarlab.core.usecases.user

import com.monstarlab.arch.extensions.SealedResult
import com.monstarlab.core.data.repositories.UserRepository
import com.monstarlab.core.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    fun login(email: String, password: String): Flow<SealedResult<User>> = flow {
        userRepository.login(email, password)
        emit(SealedResult { userRepository.getUser() })
    }
}