package com.fastcampus.domain.usecase.login

interface GetTokenUseCase {
    suspend operator fun invoke():String?
}