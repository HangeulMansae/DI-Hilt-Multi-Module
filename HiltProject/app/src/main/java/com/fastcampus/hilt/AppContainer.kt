package com.fastcampus.hilt

import android.content.Context

/**
 * @author soohwan.ok
 */
class AppContainer constructor(private val context: Context) {

//    fun createRetrofit():Retrofit {
//        return Retrofit.Builder()
//            .baseUrl("http://localhost:8080/api/")
//            .addConverterFactory(Json.asConverterFactory("application/json; charset=UTF8".toMediaType()))
//            .build()
//    }
//
//
//    fun createLoginRetrofitService(): LoginRetrofitService {
//        return createRetrofit().create(LoginRetrofitService::class.java)
//    }
//
//    fun createUserLocalDataSource(): UserLocalDataSource {
//        return UserLocalDataSource(context)
//    }
//
//    fun createUserRemoteDataSource(): UserRemoteDataSource {
//        return UserRemoteDataSource(createLoginRetrofitService())
//    }
//
//    var loginContainer: LoginContainer? = null
}
