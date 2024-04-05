package com.sopt.now.util

import android.content.Context
import com.google.gson.Gson
import com.sopt.now.model.User

class preferenceUtil(context: Context) {
    private val userdata = context.getSharedPreferences("userData", Context.MODE_PRIVATE)

    fun getString(key: String): User {
        if (userdata.getString(key, null) != null) {
            return Gson().fromJson(userdata.getString(key, null), User::class.java)
        } else {
            return User("", "", "", "")
        }

    }

    fun setString(key: String, value: String) {
        userdata.edit().putString(key, value).apply()
    }

    fun getBoolean(key: String): Boolean {
        return userdata.getBoolean(key, false)
    }

    fun setBoolean(key: String, value: Boolean) {
        userdata.edit().putBoolean(key, value).apply()
    }

    fun clearUserData() {
        userdata.edit().clear().apply()
    }
}