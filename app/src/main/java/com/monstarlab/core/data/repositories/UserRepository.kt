package com.monstarlab.core.data.repositories

import com.monstarlab.arch.data.Repository
import com.monstarlab.arch.extensions.*
import com.monstarlab.core.data.mappers.toUser
import com.monstarlab.core.data.network.Api
import com.monstarlab.core.data.network.responses.TokenResponse
import com.monstarlab.core.data.network.responses.UserResponse
import com.monstarlab.core.data.storage.UserPreferenceStore
import com.monstarlab.core.domain.error.ErrorModel
import com.monstarlab.core.domain.model.User
import java.lang.RuntimeException
import javax.inject.Inject
import kotlin.onSuccess

class UserRepository @Inject constructor(
        private val api: Api,
        private val userPreferenceStore: UserPreferenceStore
): Repository() {

    suspend fun login(email: String, password: String): TokenResponse = repoCall {
        api.postLogin(email, password)
    }

    suspend fun getUser(): User  {
        return api.getUser()
            .mapSuccess {
                 it.data.toUser()
            }.also {
                userPreferenceStore.add(it)
            }
    }

}