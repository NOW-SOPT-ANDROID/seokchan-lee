package com.sopt.now.compose.data.datasource

interface SharedPrefDataSource {
    fun setUserId(key: String, value: Int)
    fun getUserId(key: String): Int
    fun clearUserData()
}