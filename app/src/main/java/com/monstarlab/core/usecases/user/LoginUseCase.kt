package com.monstarlab.core.usecases.user

import com.monstarlab.arch.extensions.*
import com.monstarlab.core.data.network.responses.TokenResponse
import com.monstarlab.core.data.repositories.UserRepository
import com.monstarlab.core.domain.model.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
        private val userRepository: UserRepository
) {

    fun login(email: String, password: String): Flow<UseCaseResult<User>> = safeFlow {
        delay(2000)
        userRepository.login(email, password).onSuccess {
            // Insert into local token store
        }.onError {
            return@safeFlow RepositoryResult.Error(it)
        }

        userRepository.getUser()
    }

}