package com.monstarlab.core.data.network.dtos

import kotlinx.serialization.Serializable

@Serializable
data class ResourceDto(
    val id: Int,
    val name: String,
    val year: Int,
    val color: String,
    val pantone_value: String
)
