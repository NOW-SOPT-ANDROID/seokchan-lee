package com.sopt.now.data.repository

import com.sopt.now.data.datasource.SharedPrefDataSource
import javax.inject.Inject

class SharedPrefRepositoryImpl @Inject constructor(
    private val sharedPrefDataSource: SharedPrefDataSource
) :SharedPrefRepository {
    override fun setUserId(key: String, value: Int) {
        sharedPrefDataSource.setUserId(key, value)
    }

    override fun getUserId(key: String): Int {
        return sharedPrefDataSource.getUserId(key)
    }

    override fun getBoolean(key: String): Boolean {
        return sharedPrefDataSource.getBoolean(key)
    }

    override fun setBoolean(key: String, value: Boolean) {
        sharedPrefDataSource.setBoolean(key, value)
    }

    override fun clearUserData() {
        sharedPrefDataSource.clearUserData()
    }
}