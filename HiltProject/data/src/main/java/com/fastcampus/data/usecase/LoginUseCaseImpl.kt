package com.fastcampus.data.usecase

import com.fastcampus.data.model.LoginParam
import com.fastcampus.data.retrofit.UserService
import com.fastcampus.domain.usecase.login.LoginUseCase
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private val userService: UserService
) : LoginUseCase{
    override suspend fun invoke(id: String, password: String): Result<String> = kotlin.runCatching {

        val requestBody = LoginParam(id, password).toRequestBody()
        userService.login(requestBody = requestBody).data
    }
}