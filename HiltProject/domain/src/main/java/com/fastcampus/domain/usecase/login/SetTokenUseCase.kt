package com.fastcampus.domain.usecase.login

interface SetTokenUseCase {
    suspend operator fun invoke(token: String)
}