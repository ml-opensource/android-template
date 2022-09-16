package com.monstarlab.core.data.network.dtos

import com.monstarlab.features.resources.domain.Resource
import com.monstarlab.features.resources.data.ResourceDto

fun ResourceDto.toEntity(): Resource {
    return Resource(
        id = id,
        name = name,
        year = year,
        color = color,
        pantoneValue = pantoneValue
    )
}
