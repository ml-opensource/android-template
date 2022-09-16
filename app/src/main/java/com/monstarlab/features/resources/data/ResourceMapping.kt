package com.monstarlab.features.resources.data

import com.monstarlab.features.resources.data.ResourceDto
import com.monstarlab.features.resources.domain.Resource

fun ResourceDto.toEntity(): Resource {
    return Resource(
        id = id,
        name = name,
        year = year,
        color = color,
        pantoneValue = pantoneValue
    )
}
