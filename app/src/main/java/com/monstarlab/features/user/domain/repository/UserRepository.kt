package com.monstarlab.features.user.domain.repository

import com.monstarlab.features.user.domain.model.User

interface UserRepository {
    suspend fun get(): User
}
