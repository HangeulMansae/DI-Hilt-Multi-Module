package com.fastcampus.domain.usecase.login

interface ClearTokenUseCase {
    suspend operator fun invoke()
}