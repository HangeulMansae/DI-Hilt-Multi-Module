package com.fastcampus.practice

import android.util.Log
import com.fastcampus.practice.ui.MyName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ElementsIntoSet
import dagger.multibindings.IntKey
import dagger.multibindings.IntoMap
import dagger.multibindings.IntoSet
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

    @Provides
    @IntoSet
    fun provideOneString(): String{
        return "ABC"
    }

    @Provides
     @ElementsIntoSet
    fun provideMultipleString(): Set<String>{
        return listOf("DEF, GHI").toSet()
    }

    @Provides
    @IntoMap
    @IntKey(100)
    fun provideIntString(): String{
        return "백점"
    }

    @Provides
    @IntoMap
    @IntKey(90)
    fun provideIntString2(): String{
        return "구십점"
    }

    @Provides
    @IntoMap
    @AnimalKey(Animal.DOG)
    fun provideDog():String{
        return "멍멍이"
    }

    @Provides
    @IntoMap
    @AnimalKey(Animal.CAT)
    fun provideCat():String{
        return "고양이"
    }
}