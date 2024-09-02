package com.fastcampus.practice

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import kotlin.random.Random

@Module
@InstallIn(DialogComponent::class)
class DialogModule {
    @Provides
    fun provideUser(): User{
        return User("Chrales")
    }

    @Provides
    @DialogScoped
    fun provideRandomNumber(): Int{
        return Random.nextInt(1000)
    }
}