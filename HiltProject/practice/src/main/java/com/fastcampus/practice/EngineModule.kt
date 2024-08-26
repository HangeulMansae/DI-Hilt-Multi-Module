package com.fastcampus.practice

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class EngineModule {

    // 이렇게 하면 똑같이 Engine이라는 의존성을 반환하는데, Qualifier나 Naeme이 없기 때문에 오류가 남
//    @Binds
//    abstract fun bindGasolineEngine(engine:GasolineEngine): Engine
//
//    @Binds
//    abstract fun bindDieselEngine(engine:DieselEngine): Engine

//    @Binds
//    abstract fun bindGasolineEngine(engine:GasolineEngine): Engine

    @Binds
    abstract fun bindDieselEngine(engine:DieselEngine): Engine
}