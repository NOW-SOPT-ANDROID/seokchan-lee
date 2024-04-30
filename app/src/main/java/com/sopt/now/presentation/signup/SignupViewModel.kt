package com.sopt.now.presentation.signup

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.sopt.now.MyApplication
import com.sopt.now.R
import com.sopt.now.data.preferenceUtil
import com.sopt.now.presentation.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(private val sharedPreferences: preferenceUtil): ViewModel() {


    fun setUserData(userInfo: User) {
        val json = Gson().toJson(userInfo)
        sharedPreferences.setString(PREF_KEY, json)
    }

    fun checkInvalidSignup(userInfo: User): SignupState {
        return when {
            userInfo.nickname.isEmpty() -> SignupState(false, R.string.signup_empty_nickname)
            userInfo.mbti.isEmpty() -> SignupState(false, R.string.signup_empty_mbti)
            userInfo.id.length !in 6..10 -> SignupState(false, R.string.signup_empty_id)
            userInfo.pw.length !in 8..12 -> SignupState(false, R.string.signup_empty_pw)
            else -> SignupState(true, R.string.login_signup_success)
        }
    }

    companion object {
        private const val PREF_KEY = "userData"
    }
}