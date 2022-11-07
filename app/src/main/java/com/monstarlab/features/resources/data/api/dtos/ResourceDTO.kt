package com.monstarlab.features.resources.data.api.dtos

import com.monstarlab.features.resources.domain.Resource
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResourceDTO(
    val id: Int,
    val name: String,
    val year: Int,
    val color: String,
    @SerialName("pantone_value")
    val pantoneValue: String
)

fun ResourceDTO.toResource(): Resource {
    return Resource(
        id = id,
        name = name,
        year = year,
        color = color,
        pantoneValue = pantoneValue
    )
}
