package com.sopt.now.data.model.service

import com.sopt.now.data.model.remote.response.ResponseReqresDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ReqresService {

    @GET("api/users")
    fun getReqresPage(
        @Query("page") pageNumber: Int,
    ): Call<ResponseReqresDto>
}