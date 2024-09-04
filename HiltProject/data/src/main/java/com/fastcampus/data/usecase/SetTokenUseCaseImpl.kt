package com.fastcampus.data.usecase

import com.fastcampus.UserDataStore
import com.fastcampus.domain.usecase.login.SetTokenUseCase
import javax.inject.Inject

class SetTokenUseCaseImpl @Inject constructor(private val userDataStore: UserDataStore): SetTokenUseCase {
    override suspend fun invoke(token: String) {
        userDataStore.setToken(token)
    }
}