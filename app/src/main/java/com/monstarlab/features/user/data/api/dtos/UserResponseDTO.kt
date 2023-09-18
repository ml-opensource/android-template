package com.monstarlab.features.user.data.api.dtos

import kotlinx.serialization.Serializable

@Serializable
data class UserResponseDTO(
    val data: UserDTO
)
