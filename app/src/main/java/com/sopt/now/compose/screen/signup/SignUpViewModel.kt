package com.sopt.now.compose.screen.signup

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.sopt.now.compose.MyApplication
import com.sopt.now.compose.R
import com.sopt.now.compose.screen.model.User

class SignUpViewModel : ViewModel() {

    private val id: MutableLiveData<String> = MutableLiveData("")
    private val pw: MutableLiveData<String> = MutableLiveData("")
    private val nickname: MutableLiveData<String> = MutableLiveData("")
    private val mbti: MutableLiveData<String> = MutableLiveData("")

    fun updateUserInfo(userId: String, userPw: String, userNickname: String, userMbti: String) {
        id.value = userId
        pw.value = userPw
        nickname.value = userNickname
        mbti.value = userMbti
        Log.d("asd", id.value.toString())
    }


    fun checkInvalidSignup(): signup {
        return when {
            id.value.toString().length !in MIN_ID..MAX_ID ->
                signup(false, R.string.signup_screen_invalid_id)

            pw.value.toString().length !in MIN_PW..MAX_PW ->
                signup(false, R.string.signup_screen_invalid_pw)

            nickname.value.isNullOrBlank() -> signup(false, R.string.signup_screen_empty_nickname)
            mbti.value.isNullOrBlank() ->
                signup(false, R.string.signup_screen_empty_mbti)

            else -> {
                setUserData()
                signup(true, R.string.login_screen_success_signup)
            }
        }
    }

    private fun setUserData() {
        val user = User(
            id.value.toString(),
            pw.value.toString(),
            nickname.value.toString(),
            mbti.value.toString(),
        )
        val json = Gson().toJson(user)
        MyApplication.userdata.setString(PREF_KEY, json)
    }

    companion object {
        private const val PREF_KEY = "USER_DATA"
        private const val MIN_ID = 6
        private const val MAX_ID = 10
        private const val MIN_PW = 8
        private const val MAX_PW = 12

    }
}
