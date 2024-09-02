package com.fastcampus.hilt

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import kr.co.fastcampus.sns.LoginContainer
import kr.co.fastcampus.sns.LoginRetrofitService
import kr.co.fastcampus.sns.UserLocalDataSource
import kr.co.fastcampus.sns.UserRemoteDataSource
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

/**
 * @author soohwan.ok
 */
class AppContainer constructor(private val context: Context) {

    fun createRetrofit():Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://localhost:8080/api/")
            .addConverterFactory(Json.asConverterFactory("application/json; charset=UTF8".toMediaType()))
            .build()
    }


    fun createLoginRetrofitService(): LoginRetrofitService {
        return createRetrofit().create(LoginRetrofitService::class.java)
    }

    fun createUserLocalDataSource(): UserLocalDataSource {
        return UserLocalDataSource(context)
    }

    fun createUserRemoteDataSource(): UserRemoteDataSource {
        return UserRemoteDataSource(createLoginRetrofitService())
    }

    var loginContainer: LoginContainer? = null
}
