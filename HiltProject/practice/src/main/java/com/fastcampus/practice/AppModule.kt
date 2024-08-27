package com.fastcampus.practice

import android.util.Log
import com.fastcampus.practice.ui.MyName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val TAG = "AppModule_싸피"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMyName(): MyName {
        Log.e(TAG, "provideMyName: 호출", )
        return MyName()
    }

    @Provides
    fun provideFoo(): Foo {
        return Foo()
    }
}