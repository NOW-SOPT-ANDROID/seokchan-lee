package com.sopt.now.presentation.main

import androidx.lifecycle.ViewModel
import com.sopt.now.MyApplication
import com.sopt.now.data.PreferenceUtil
import com.sopt.now.presentation.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val sharedPreferences: PreferenceUtil) :
    ViewModel() {
    fun getUserData(): User {
        return sharedPreferences.getString(PREF_KEY)
    }

    companion object {
        private const val PREF_KEY = "userData"
    }
}