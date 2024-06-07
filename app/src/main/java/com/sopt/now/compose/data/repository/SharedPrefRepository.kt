package com.sopt.now.compose.data.repository

interface SharedPrefRepository {
    fun setUserId(key: String, value: Int)
    fun getUserId(key: String): Int
    fun clearUserData()
}