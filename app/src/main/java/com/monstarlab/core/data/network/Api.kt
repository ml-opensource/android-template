package com.monstarlab.core.data.network

import com.monstarlab.core.data.network.dtos.PostDto
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("posts")
    suspend fun getPosts(): Response<List<PostDto>>
}