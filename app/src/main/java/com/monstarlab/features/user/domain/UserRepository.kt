package com.monstarlab.features.user.domain

import com.monstarlab.arch.data.Repository
import com.monstarlab.arch.extensions.mapSuccess
import com.monstarlab.features.user.data.models.toUser
import com.monstarlab.features.user.data.UserPreferenceStore
import com.monstarlab.features.user.data.UsersApi
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
