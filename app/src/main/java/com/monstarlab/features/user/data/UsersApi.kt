package com.monstarlab.features.user.data

import com.monstarlab.features.user.data.models.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface UsersApi {

    @GET("users/2")
    suspend fun getUser(): Response<UserResponse>
}
