package com.sopt.now.compose.data.repository

import com.sopt.now.compose.data.datasource.SharedPrefDataSource
import javax.inject.Inject

class SharedPrefRepositoryImpl @Inject constructor(
    private val sharedPrefDataSource: SharedPrefDataSource
) : SharedPrefRepository {
    override fun setUserId(key: String, value: Int) {
        sharedPrefDataSource.setUserId(key, value)
    }

    override fun getUserId(key: String): Int {
        return sharedPrefDataSource.getUserId(key)
    }

    override fun clearUserData() {
        sharedPrefDataSource.clearUserData()
    }
}