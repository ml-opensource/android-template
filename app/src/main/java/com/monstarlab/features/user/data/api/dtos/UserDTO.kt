package com.monstarlab.features.user.data.api.dtos

import com.monstarlab.features.user.domain.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    val id: Int,
    val email: String,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String,
    val avatar: String
)

fun UserDTO.toUser(): User {
    return User(
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar
    )
}
