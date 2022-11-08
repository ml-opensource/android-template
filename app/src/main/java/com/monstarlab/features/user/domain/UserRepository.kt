package com.monstarlab.features.user.domain

import com.monstarlab.core.persistence.Repository
import com.monstarlab.features.user.data.api.UsersApi
import com.monstarlab.features.user.data.api.dtos.toUser
import com.monstarlab.features.user.data.storage.UserPreferenceStore
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: UsersApi,
    private val userPreferenceStore: UserPreferenceStore
) : Repository() {

    suspend fun getUser(): User {
        return api.getUser().data.toUser().also {
            userPreferenceStore.add(it)
        }
    }
}
