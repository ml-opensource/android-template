package com.monstarlab.core.data.network

import com.monstarlab.core.data.network.dtos.PostDto
import com.monstarlab.core.data.network.responses.ResourcesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("posts")
    suspend fun getPosts(): Response<List<PostDto>>



    @GET("unknown")
    suspend fun getResources(@Query("page") page: Int = 1): Response<ResourcesResponse>
}
