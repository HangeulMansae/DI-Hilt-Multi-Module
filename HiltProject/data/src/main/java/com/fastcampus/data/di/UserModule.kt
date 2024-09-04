package com.fastcampus.data.di

import com.fastcampus.data.usecase.ClearTokenUseCaseImpl
import com.fastcampus.data.usecase.GetTokenUseCaseImpl
import com.fastcampus.data.usecase.LoginUseCaseImpl
import com.fastcampus.data.usecase.SetTokenUseCaseImpl
import com.fastcampus.data.usecase.SignUpUseCaseImpl
import com.fastcampus.domain.usecase.login.ClearTokenUseCase
import com.fastcampus.domain.usecase.login.GetTokenUseCase
import com.fastcampus.domain.usecase.login.LoginUseCase
import com.fastcampus.domain.usecase.login.SetTokenUseCase
import com.fastcampus.domain.usecase.login.SignUpUseCase
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

    @Binds
    abstract fun bindGetTokenUseCase(uc: GetTokenUseCaseImpl): GetTokenUseCase

    @Binds
    abstract fun bindSetTokenUseCase(uc: SetTokenUseCaseImpl): SetTokenUseCase

    @Binds
    abstract fun bindClearTokenUseCase(uc: ClearTokenUseCaseImpl): ClearTokenUseCase

//    @Provides
//    fun provideLoginUseCase(): LoginUseCase{
//        return LoginUseCaseImpl()
//    }
}