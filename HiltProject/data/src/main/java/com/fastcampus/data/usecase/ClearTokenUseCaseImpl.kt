package com.fastcampus.data.usecase

import com.fastcampus.UserDataStore
import com.fastcampus.domain.usecase.login.ClearTokenUseCase
import javax.inject.Inject

class ClearTokenUseCaseImpl @Inject constructor(
    private val userDataStore: UserDataStore
): ClearTokenUseCase {
    override suspend fun invoke(): Result<Unit> = kotlin.runCatching{
        userDataStore.clear()
    }
}