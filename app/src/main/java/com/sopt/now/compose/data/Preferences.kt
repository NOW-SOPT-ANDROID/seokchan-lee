package com.sopt.now.compose.data

import android.content.Context
import com.google.gson.Gson
import com.sopt.now.compose.screen.model.User

class Preferences(
    private val context: Context,
) {
    private val userdata = context.getSharedPreferences("userData", Context.MODE_PRIVATE)


    fun setUserId(key: String, value: Int) {
        return userdata.edit().putInt(key, value).apply()
    }

    fun getUserId(key: String): Int {
        return userdata.getInt(key, 0)
    }

    fun clearUserData() {
        userdata.edit().clear().apply()
    }
}