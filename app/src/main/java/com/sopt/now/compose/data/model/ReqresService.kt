package com.sopt.now.compose.data.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ReqresService {

    @GET("api/users")
    fun getReqresPage(
        @Query("page") pageNumber: Int,
    ): Call<ResponseReqresDto>
}