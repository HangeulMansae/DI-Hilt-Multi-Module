package com.fastcampus.practice

import com.fastcampus.practice.ui.MyName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideMyName(): MyName {
        return MyName()
    }
}