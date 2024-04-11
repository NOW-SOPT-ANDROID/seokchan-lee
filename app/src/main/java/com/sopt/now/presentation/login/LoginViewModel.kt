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

    fun checkInvalidLogin() {
        val userData = getUserData()
        when {
            id.value.toString().isBlank() -> _loginState.value =
                LoginState.Failure(R.string.signup_empty_id)

            pw.value.toString().isBlank() -> _loginState.value =
                LoginState.Failure(R.string.signup_empty_pw)

            id.value.toString() == userData.id && pw.value.toString() == userData.pw -> {
                MyApplication.userdata.setBoolean(LOGIN_STATE_KEY, true)
                loginStateChange()
                _loginState.value = LoginState.Success(R.string.login_success_login)
            }

            else -> _loginState.value = LoginState.Empty
        }

    }

    fun updateId(msg: String) {
        id.value = msg
    }

    fun updatePw(msg: String) {
        pw.value = msg
    }

    companion object {
        private const val PREF_KEY = "userData"
        private const val LOGIN_STATE_KEY = "true"
    }
}