package com.sopt.now.presentation.main

import androidx.lifecycle.ViewModel
import com.sopt.now.MyApplication
import com.sopt.now.model.User

class MainViewModel : ViewModel() {
    fun getUserData(): User {
        return MyApplication.userdata.getString(PREF_KEY)
    }

    companion object {
        private const val PREF_KEY = "userData"
    }
}