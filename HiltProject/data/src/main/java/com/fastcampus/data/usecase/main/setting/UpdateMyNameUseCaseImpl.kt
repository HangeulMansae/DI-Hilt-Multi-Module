package com.fastcampus.data.usecase.main.setting

import com.fastcampus.data.model.UpdateMyInfoParam
import com.fastcampus.data.retrofit.UserService
import com.fastcampus.domain.usecase.main.setting.GetMyUserUseCase
import com.fastcampus.domain.usecase.main.setting.UpdateMyNameUseCase
import javax.inject.Inject

class UpdateMyNameUseCaseImpl @Inject constructor(
    private val userService: UserService,
    private val getMyUserUseCase: GetMyUserUseCase
): UpdateMyNameUseCase {
    override suspend operator fun invoke(
        username: String
    ):Result<Unit> = kotlin.runCatching {
        val user = getMyUserUseCase().getOrThrow()
        val requestBody = UpdateMyInfoParam(
            userName = username,
            profileFilePath = user.profileImageUrl.orEmpty(),
            extraUserInfo = ""
        ).toRequestBody()
        userService.patchMyPage(requestBody)
    }
}