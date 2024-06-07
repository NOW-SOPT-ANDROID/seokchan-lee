package com.sopt.now.compose.data.datasourceImpl

import android.content.SharedPreferences
import com.sopt.now.compose.data.datasource.SharedPrefDataSource
import javax.inject.Inject

class SharedPrefDataSourceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : SharedPrefDataSource{

    override fun setUserId(key: String, value: Int) {
        sharedPreferences.edit().putInt(key,value).apply()
    }

    override fun getUserId(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }

    override fun clearUserData() {
        sharedPreferences.edit().clear().apply()
    }
}