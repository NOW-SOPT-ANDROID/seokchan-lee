package com.sopt.now.data.model.service

import com.sopt.now.data.model.remote.request.RequestSignInDto
import com.sopt.now.data.model.remote.request.RequestSignUpDto
import com.sopt.now.data.model.remote.response.ResponseSignInDto
import com.sopt.now.data.model.remote.response.ResponseSignUpDto
import com.sopt.now.data.model.remote.response.ResponseUserInfoDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    fun signUp(
        @Body request: RequestSignUpDto,
    ): Call<ResponseSignUpDto>

    @POST("member/login")
    fun signIn(
        @Body request: RequestSignInDto,
    ): Call<ResponseSignInDto>

    @GET("member/info")
    fun getUserInfo(
        @Header("memberId") userId: Int,
    ): Call<ResponseUserInfoDto>
}