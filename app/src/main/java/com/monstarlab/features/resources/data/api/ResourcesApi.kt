package com.monstarlab.features.resources.data.api

import com.monstarlab.features.resources.data.api.dtos.ResourcesResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ResourcesApi {

    @GET("unknown")
    suspend fun getResources(@Query("page") page: Int = 1): ResourcesResponseDTO
}
