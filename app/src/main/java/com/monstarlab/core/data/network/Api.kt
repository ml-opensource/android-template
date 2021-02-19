package com.monstarlab.core.data.network

import com.monstarlab.core.data.network.dtos.PostDto
import com.monstarlab.core.data.network.responses.ResourcesResponse
import com.monstarlab.core.data.network.responses.TokenResponse
import com.monstarlab.core.data.network.responses.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface Api {
    @GET("posts")
    suspend fun getPosts(): Response<List<PostDto>>

    @FormUrlEncoded
    @POST("login")
    suspend fun postLogin(@Field("email") email: String, @Field("password") password: String): Response<TokenResponse>

    @GET("users/2")
    suspend fun getUser(): Response<UserResponse>

    @GET("unknown")
    suspend fun getResources(@Query("page") page: Int = 1): Response<ResourcesResponse>
}