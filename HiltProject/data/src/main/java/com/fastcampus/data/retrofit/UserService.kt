package com.fastcampus.data.retrofit

import com.fastcampus.data.model.CommonResponse
import com.fastcampus.data.model.UserDTO
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface UserService {
    @POST("users/login")
    // OKHTTP에 Interceptor로 Header를 넣어줬으므로 더이상 작성하지 않아도 됨
//    @Headers("Content-Type:application/json; charset=UTF8")
    suspend fun login(
        @Body requestBody: RequestBody
    ):CommonResponse<String>

    @POST("users/sign-up")
//    @Headers("Content-Type:application/json; charset=UTF8")
    suspend fun signUp(
        @Body requestBody: RequestBody
    ): CommonResponse<Int>

    @GET("users/my-page")
//    @Headers("Content-Type:application/json; charset=UTF8")
    suspend fun getMyPage(): CommonResponse<UserDTO>

    @PATCH("users/my-page")
//    @Headers("Content-Type:application/json; charset=UTF8")
    suspend fun patchMyPage(
        @Body requestBody: RequestBody
    ): CommonResponse<Long>

}