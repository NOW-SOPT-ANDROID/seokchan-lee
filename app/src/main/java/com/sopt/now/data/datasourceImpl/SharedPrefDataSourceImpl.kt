package com.sopt.now.data.datasourceImpl

import android.content.SharedPreferences
import com.sopt.now.data.datasource.SharedPrefDataSource
import javax.inject.Inject

class SharedPrefDataSourceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : SharedPrefDataSource {

    override fun setUserId(key: String, value: Int) {
        return sharedPreferences.edit().putInt(key, value).apply()
    }

    override fun getUserId(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }

    override fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    override fun setBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    override fun clearUserData() {
        sharedPreferences.edit().clear().apply()
    }
}