package com.fastcampus.data.usecase.main.setting

import com.fastcampus.data.model.UpdateMyInfoParam
import com.fastcampus.data.retrofit.UserService
import com.fastcampus.domain.usecase.main.setting.GetMyUserUseCase
import com.fastcampus.domain.usecase.main.setting.SetMyUserUseCase
import javax.inject.Inject

class SetMyUserUseCaseImpl @Inject constructor(
    private val userService: UserService,
    private val getMyUserUseCase: GetMyUserUseCase
): SetMyUserUseCase {
    override suspend operator fun invoke(
        username: String?,
        profileImageUrl: String?
    ):Result<Unit> = kotlin.runCatching {
        val user = getMyUserUseCase().getOrThrow()
        val requestBody = UpdateMyInfoParam(
            userName = username ?: user.username,
            profileFilePath = profileImageUrl ?: user.profileImageUrl.orEmpty(),
            extraUserInfo = ""
        ).toRequestBody()
        userService.patchMyPage(requestBody)
    }
}