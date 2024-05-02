package com.sopt.now.data

import android.content.SharedPreferences
import com.google.gson.Gson
import com.sopt.now.presentation.model.User
import javax.inject.Inject

class PreferenceUtil @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun getString(key: String): User {
        return if (sharedPreferences.getString(key, null) != null) {
            Gson().fromJson(sharedPreferences.getString(key, null), User::class.java)
        } else {
            User("", "", "", "")
        }
    }

    fun setString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
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