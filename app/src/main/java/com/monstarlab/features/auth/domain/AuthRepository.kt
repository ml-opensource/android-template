package com.monstarlab.features.auth.domain

import com.monstarlab.features.auth.data.AuthApi
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: AuthApi
) {

    suspend fun login(email: String, password: String): AuthToken {
        val responseBody = requireNotNull(api.postLogin(email, password).body())
        return AuthToken(responseBody.token)
    }
}
