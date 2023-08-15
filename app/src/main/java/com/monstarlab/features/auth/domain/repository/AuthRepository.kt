package com.monstarlab.features.auth.domain.repository

import com.monstarlab.features.auth.domain.models.AuthToken


interface AuthRepository {
    suspend fun login(email: String, password: String) : AuthToken
}