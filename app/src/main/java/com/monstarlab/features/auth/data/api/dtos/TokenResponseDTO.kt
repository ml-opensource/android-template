package com.monstarlab.features.auth.data.api.dtos

import kotlinx.serialization.Serializable

@Serializable
data class TokenResponseDTO(
    val token: String
)
