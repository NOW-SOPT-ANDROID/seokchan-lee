package com.sopt.now.data.datasource

interface SharedPrefDataSource {
    fun setUserId(key: String, value: Int)
    fun getUserId(key: String): Int
    fun getBoolean(key: String): Boolean
    fun setBoolean(key: String, value: Boolean)
    fun clearUserData()
}