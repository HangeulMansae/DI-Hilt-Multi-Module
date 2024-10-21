package com.fastcampus.data.di

import com.fastcampus.data.usecase.ClearTokenUseCaseImpl
import com.fastcampus.data.usecase.GetTokenUseCaseImpl
import com.fastcampus.data.usecase.LoginUseCaseImpl
import com.fastcampus.data.usecase.SetTokenUseCaseImpl
import com.fastcampus.data.usecase.SignUpUseCaseImpl
import com.fastcampus.data.usecase.main.setting.GetMyUserUseCaseImpl
import com.fastcampus.data.usecase.main.setting.SetMyUserUseCaseImpl
import com.fastcampus.data.usecase.main.setting.SetProfileImageUseCaseImpl
import com.fastcampus.domain.usecase.login.ClearTokenUseCase
import com.fastcampus.domain.usecase.login.GetTokenUseCase
import com.fastcampus.domain.usecase.login.LoginUseCase
import com.fastcampus.domain.usecase.login.SetTokenUseCase
import com.fastcampus.domain.usecase.login.SignUpUseCase
import com.fastcampus.domain.usecase.main.setting.GetMyUserUseCase
import com.fastcampus.domain.usecase.main.setting.SetMyUserUseCase
import com.fastcampus.domain.usecase.main.setting.SetProfileImageUseCase
import dagger.Binds
import dagger.Module
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

    @Binds
    abstract fun bindGetMyUserUseCase(uc: GetMyUserUseCaseImpl): GetMyUserUseCase

    @Binds
    abstract fun bindUpdateMyNameUseCase(uc: SetMyUserUseCaseImpl): SetMyUserUseCase

    @Binds
    abstract fun bindSetProfileImageUseCase(uc: SetProfileImageUseCaseImpl): SetProfileImageUseCase

//    @Provides
//    fun provideLoginUseCase(): LoginUseCase{
//        return LoginUseCaseImpl()
//    }
}