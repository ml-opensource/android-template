package com.monstarlab.features.user.data.models

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val data: UserDto
)
