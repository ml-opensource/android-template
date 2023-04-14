package com.monstarlab.features.auth.domain

import com.monstarlab.features.auth.data.api.AuthApi
import com.monstarlab.features.auth.domain.models.AuthToken
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: AuthApi
) {

    suspend fun login(email: String, password: String): AuthToken {
        val responseBody = api.postLogin(email, password)
        return AuthToken(responseBody.token)
    }
}
