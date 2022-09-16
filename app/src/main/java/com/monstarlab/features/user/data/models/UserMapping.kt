package com.monstarlab.features.user.data.models

import com.monstarlab.features.user.domain.User

fun UserDto.toUser(): User {
    return User(
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar
    )
}
