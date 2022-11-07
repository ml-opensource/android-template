package com.monstarlab.features.user.data.api

import com.monstarlab.features.user.data.api.dtos.UserResponseDTO
import retrofit2.Response
import retrofit2.http.GET

interface UsersApi {

    @GET("users/2")
    suspend fun getUser(): Response<UserResponseDTO>
}
