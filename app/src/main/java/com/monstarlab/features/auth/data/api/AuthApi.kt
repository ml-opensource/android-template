package com.monstarlab.features.auth.data.api

import com.monstarlab.features.auth.data.api.dtos.TokenResponseDTO
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    @FormUrlEncoded
    @POST("login")
    suspend fun postLogin(@Field("email") email: String, @Field("password") password: String): TokenResponseDTO
}
