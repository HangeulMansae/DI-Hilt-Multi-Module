package com.fastcampus.domain.usecase.main.setting

import com.fastcampus.domain.usecase.model.User

interface GetMyUserUseCase {
    suspend operator fun invoke(): Result<User>
}