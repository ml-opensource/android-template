package com.monstarlab.features.auth.data.api.dtos

import com.monstarlab.features.auth.domain.models.AuthToken
import kotlinx.serialization.Serializable

@Serializable
data class TokenResponseDTO(
    val token: String,
)

fun TokenResponseDTO.toAuthToken() = AuthToken(token)
