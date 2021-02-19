package com.monstarlab.core.data.network.responses

import com.monstarlab.core.data.network.dtos.ResourceDto
import kotlinx.serialization.Serializable

@Serializable
data class ResourcesResponse(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data: List<ResourceDto>
)
