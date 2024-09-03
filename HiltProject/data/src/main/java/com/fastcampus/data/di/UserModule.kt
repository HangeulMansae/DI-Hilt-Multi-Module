package com.fastcampus.data.di

import com.fastcampus.data.usecase.LoginUseCaseImpl
import com.ijonsabae.domain.usecase.login.LoginUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UserModule {

    @Binds
    abstract fun bindLoginUseCase(uc: LoginUseCaseImpl): LoginUseCase

//    @Provides
//    fun provideLoginUseCase(): LoginUseCase{
//        return LoginUseCaseImpl()
//    }
}