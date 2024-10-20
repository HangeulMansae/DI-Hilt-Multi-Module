package com.fastcampus.data.usecase.main.setting

import com.fastcampus.data.model.toDomainModel
import com.fastcampus.data.retrofit.UserService
import com.fastcampus.domain.usecase.main.setting.GetMyUserUseCase
import com.fastcampus.domain.usecase.model.User
import javax.inject.Inject

class GetMyUserUseCaseImpl @Inject constructor (private val userService: UserService): GetMyUserUseCase {
    override suspend fun invoke(): Result<User> = kotlin.runCatching {
        userService.getMyPage().data.toDomainModel()
    }

}