package com.fastcampus.data.di

import com.fastcampus.data.usecase.file.GetImageUseCaseImpl
import com.fastcampus.data.usecase.file.GetInputStreamUseCaseImpl
import com.fastcampus.data.usecase.file.UploadImageUseCaseImpl
import com.fastcampus.domain.usecase.file.GetImageUseCase
import com.fastcampus.domain.usecase.file.GetInputStreamUseCase
import com.fastcampus.domain.usecase.file.UploadImageUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FileModule {
    @Binds
    abstract fun bindGetInputStreamUseCase(uc: GetInputStreamUseCaseImpl): GetInputStreamUseCase

    @Binds
    abstract fun bindGetImageUseCase(uc: GetImageUseCaseImpl): GetImageUseCase

    @Binds
    abstract fun bindUploadImageUseCase(uc: UploadImageUseCaseImpl): UploadImageUseCase
}