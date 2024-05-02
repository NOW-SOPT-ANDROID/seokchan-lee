package com.sopt.now.data

import android.content.SharedPreferences
import javax.inject.Inject

class PreferenceUtil @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun setUserId(key: String, value: Int) {
        return sharedPreferences.edit().putInt(key, value).apply()
    }

    fun getUserId(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun setBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun clearUserData() {
        sharedPreferences.edit().clear().apply()
    }
}