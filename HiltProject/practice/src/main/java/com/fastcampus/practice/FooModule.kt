package com.fastcampus.practice

import androidx.collection.floatObjectMapOf
import dagger.BindsOptionalOf
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

//@Module
//@InstallIn(SingletonComponent::class)
//class FooModule {
//
//    //@CustomQualifier
//    // Named로도 중복 바인딩 처리
//    @Named("Foo")
//    @Provides
//    fun provideFoo1(): Foo{
//        return Foo(id= "Foo 1")
//    }
//
//    @Provides
//    fun provideFoo2(): Foo{
//        return Foo(id= "Foo 2")
//    }
//}
@Module
@InstallIn(SingletonComponent::class)
abstract class FooModule{
    @BindsOptionalOf
    abstract fun optionalFoo(): Foo
}