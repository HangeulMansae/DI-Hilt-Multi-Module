package com.fastcampus.data.di

import com.fastcampus.data.usecase.LoginUseCaseImpl
import com.fastcampus.data.usecase.SignUpUseCaseImpl
import com.ijonsabae.domain.usecase.login.LoginUseCase
import com.ijonsabae.domain.usecase.login.SignUpUseCase
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

    @Binds
    abstract fun bindSignUpCase(sc: SignUpUseCaseImpl): SignUpUseCase

//    @Provides
//    fun provideLoginUseCase(): LoginUseCase{
//        return LoginUseCaseImpl()
//    }
}