package com.sopt.now.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.MyApplication
import com.sopt.now.R
import com.sopt.now.presentation.model.User

class LoginViewModel : ViewModel() {
    private val _loginState: MutableLiveData<LoginState> = MutableLiveData(LoginState.Empty)
    val loginState: LiveData<LoginState> = _loginState

    private val id: MutableLiveData<String> = MutableLiveData("")
    private val pw: MutableLiveData<String> = MutableLiveData("")

    init {
        loginStateChange()
    }

    private fun loginStateChange() {
        if (MyApplication.userdata.getBoolean(LOGIN_STATE_KEY))
            _loginState.value = LoginState.Success(R.string.login_success_login)
    }

    fun getUserData(): User {
        return MyApplication.userdata.getString(PREF_KEY)
    }

    fun checkInvalidLogin(inputId: String, inputPw: String, userData: User) {
        when {
            inputId.isBlank() -> _loginState.value = LoginState.Failure(R.string.signup_empty_id)
            inputPw.isBlank() -> _loginState.value = LoginState.Failure(R.string.signup_empty_pw)
            inputId == userData.id && inputPw == userData.pw -> {
                MyApplication.userdata.setBoolean(LOGIN_STATE_KEY, true)
                loginStateChange()
                _loginState.value = LoginState.Success(R.string.login_success_login)
            }

            else -> _loginState.value = LoginState.Empty
        }

    }

    companion object {
        private const val PREF_KEY = "userData"
        private const val LOGIN_STATE_KEY = "true"
    }
}