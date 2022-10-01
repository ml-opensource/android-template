package com.monstarlab.features.user.domain

import com.monstarlab.core.domain.Repository
import com.monstarlab.core.extensions.mapSuccess
import com.monstarlab.features.user.data.UserPreferenceStore
import com.monstarlab.features.user.data.UsersApi
import com.monstarlab.features.user.data.models.toUser
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: UsersApi,
    private val userPreferenceStore: UserPreferenceStore
) : Repository() {

    suspend fun getUser(): User {
        return api.getUser()
            .mapSuccess {
                it.data.toUser()
            }.also {
                userPreferenceStore.add(it)
            }
    }
}
