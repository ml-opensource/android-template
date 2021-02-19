package com.monstarlab.core.data.network.dtos

import com.monstarlab.core.data.network.dtos.UserDto
import com.monstarlab.core.domain.model.User


fun UserDto.toUser(): User {
    return User(
            email = email,
            firstName = first_name,
            lastName = last_name,
            avatar = avatar
    )
}