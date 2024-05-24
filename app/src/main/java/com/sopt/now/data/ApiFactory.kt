package com.sopt.now.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sopt.now.BuildConfig
import com.sopt.now.BuildConfig.AUTH_BASE_URL
import com.sopt.now.BuildConfig.REQRES_URL
import com.sopt.now.data.model.service.AuthService
import com.sopt.now.data.model.service.ReqresService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit


object ApiFactory {

    val auth: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(AUTH_BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }
    val reqres: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(REQRES_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(): T = auth.create(T::class.java)
    inline fun <reified T> createReqres(): T = reqres.create(T::class.java)

}

object ServicePool {
    val authService = ApiFactory.create<AuthService>()
    val reqresService = ApiFactory.createReqres<ReqresService>()
}