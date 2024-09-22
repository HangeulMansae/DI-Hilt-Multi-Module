package com.fastcampus.data.model

import com.fastcampus.domain.usecase.model.User
import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    val id: Long,
    val loginId: String,
    val userName: String,
    val extraUserInfo: String,
    val profileFilePath: String,
)

fun UserDTO.toDomainModel(): User {
    return User(
        id = id,
        loginId = loginId,
        username = userName,
        profileImageUrl = profileFilePath
    )
}