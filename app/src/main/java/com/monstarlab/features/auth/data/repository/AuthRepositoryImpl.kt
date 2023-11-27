package com.monstarlab.features.auth.data.repository

import com.monstarlab.features.auth.data.api.AuthApi
import com.monstarlab.features.auth.data.api.dtos.toAuthToken
import com.monstarlab.features.auth.domain.models.AuthToken
import com.monstarlab.features.auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
) : AuthRepository {

    override suspend fun login(email: String, password: String): AuthToken {
        val responseBody = api.postLogin(email, password)
        return responseBody.toAuthToken()
    }
}
