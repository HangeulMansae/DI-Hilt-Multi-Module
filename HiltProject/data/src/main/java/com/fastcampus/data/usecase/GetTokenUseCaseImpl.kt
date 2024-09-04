package com.fastcampus.data.usecase

import com.fastcampus.UserDataStore
import com.fastcampus.domain.usecase.login.GetTokenUseCase
import javax.inject.Inject

class GetTokenUseCaseImpl @Inject constructor(
    private val userDataStore: UserDataStore
): GetTokenUseCase {
    override suspend fun invoke(): String? {
        return userDataStore.getToken()
    }
}