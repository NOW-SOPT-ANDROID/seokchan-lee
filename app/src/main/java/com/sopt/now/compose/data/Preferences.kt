package com.sopt.now.compose.data

import android.content.Context
import com.google.gson.Gson
import com.sopt.now.compose.screen.model.User

class Preferences(
    private val context: Context,
) {
    private val userdata = context.getSharedPreferences("userData", Context.MODE_PRIVATE)

    fun getString(key: String): User {
        return if (userdata.getString(key, null) != null) {
            Gson().fromJson(userdata.getString(key, null), User::class.java)
        } else {
            User("", "", "", "")
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