package com.sopt.now.presentation.signup

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.sopt.now.MyApplication
import com.sopt.now.R
import com.sopt.now.presentation.model.User

class SignupViewModel : ViewModel() {


    fun setUserData(userInfo: User) {
        val json = Gson().toJson(userInfo)
        MyApplication.userdata.setString(PREF_KEY, json)
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