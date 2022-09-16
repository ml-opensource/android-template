package com.monstarlab.features.auth.data

import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(
    val token: String
)
