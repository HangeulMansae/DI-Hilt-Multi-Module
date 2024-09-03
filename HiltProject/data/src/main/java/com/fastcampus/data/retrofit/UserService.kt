package com.fastcampus.data.retrofit

import com.fastcampus.data.model.CommonResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserService {
    @POST("users/login")
    @Headers("Content-Type:application/json; charset=UTF8")
    suspend fun login(
        @Body requestBody: RequestBody
    ):CommonResponse<String>
}