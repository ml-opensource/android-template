package com.monstarlab.features.user.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String
)
