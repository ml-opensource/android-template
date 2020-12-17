package com.monstarlab.core.data.repositories

import com.monstarlab.arch.data.Repository
import com.monstarlab.arch.extensions.*
import com.monstarlab.core.data.mappers.toUser
import com.monstarlab.core.data.network.Api
import com.monstarlab.core.data.network.responses.TokenResponse
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

    suspend fun login(email: String, password: String): RepositoryResult<TokenResponse> {
        return api.postLogin(email, password).toResult()
    }

    suspend fun getUser(): RepositoryResult<User> {
        onShouldFetch {
            val result = api.getUser().toResultAndMap { it.data.toUser() }
            result.onSuccess { user ->
                userPreferenceStore.add(user)
            }
            if(result.isError()) return result
        }

        val user = userPreferenceStore.get()

        return when {
            user != null -> RepositoryResult.Success(user)
            else -> RepositoryResult.Error(ErrorModel.DataError.NoData)
        }
    }

}