package com.sopt.now.presentation.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.sopt.now.MyApplication
import com.sopt.now.R
import com.sopt.now.data.PreferenceUtil
import com.sopt.now.presentation.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val sharedPreferences: PreferenceUtil) :
    ViewModel() {
    private val _signUpState: MutableLiveData<SignUpState> = MutableLiveData(SignUpState.Empty)
    val signupState: MutableLiveData<SignUpState> get() = _signUpState

    private val id: MutableLiveData<String> = MutableLiveData("")
    private val pw: MutableLiveData<String> = MutableLiveData("")
    private val nickname: MutableLiveData<String> = MutableLiveData("")
    private val mbti: MutableLiveData<String> = MutableLiveData("")

    fun setUserData() {
        val userInfo = User(
            id.value.toString(),
            pw.value.toString(),
            nickname.value.toString(),
            mbti.value.toString()
        )
        val json = Gson().toJson(userInfo)
        sharedPreferences.setString(PREF_KEY, json)
    }

    fun checkInvalidSignup() {
        when {
            nickname.value.toString().isBlank() -> _signUpState.value =
                SignUpState.Failure(R.string.signup_empty_nickname)

            mbti.value.toString().isBlank() -> _signUpState.value =
                SignUpState.Failure(R.string.signup_empty_mbti)

            id.value.toString().length !in 6..10 -> _signUpState.value =
                SignUpState.Failure(R.string.signup_empty_id)

            pw.value.toString().length !in 8..12 -> _signUpState.value =
                SignUpState.Failure(R.string.signup_empty_pw)

            else -> _signUpState.value = SignUpState.Success(R.string.login_signup_success)
        }
    }

    fun updateId(msg: String) {
        id.value = msg
    }

    fun updatePw(msg: String) {
        pw.value = msg
    }

    fun updateNickName(msg: String) {
        nickname.value = msg
    }

    fun updateMbti(msg: String) {
        mbti.value = msg
    }

    companion object {
        private const val PREF_KEY = "userData"

    }
}