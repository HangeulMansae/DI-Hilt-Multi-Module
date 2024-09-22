package com.fastcampus.data.retrofit

import com.fastcampus.UserDataStore
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.http.Headers
import javax.inject.Inject

class FCInterceptor @Inject constructor(
    private val userDataStore: UserDataStore
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // userDataStore에서 토큰 정보를 가져옴
        // Interceptor가 자바 기반이기 때문에 Coroutine을 쓰는 것이 어려움, 눈물을 머금고 runBlocking을 사용
        val token: String? = runBlocking { userDataStore.getToken() }
        return chain.proceed(
            chain.request()
                .newBuilder()
                .run {
                    // 만약 token 정보가 있으면 Token 헤더에 넣어줌
                    if(token.isNullOrEmpty()){
                        this
                    }else{
                        this.addHeader("Token", token)
                    }
                }
                .addHeader("Content-Type","application/json; charset=UTF8")
                .build()
        )
    }

}