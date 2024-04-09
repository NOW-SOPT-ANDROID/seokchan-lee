package com.sopt.now.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.MyApplication
import com.sopt.now.presentation.model.User

class LoginViewModel : ViewModel() {
    private val _loginState: MutableLiveData<Boolean> = MutableLiveData(false)
    val loginState: LiveData<Boolean> = _loginState

    init {
        loginStateChange()
    }

    private fun loginStateChange() {
        if (MyApplication.userdata.getBoolean(LOGIN_STATE_KEY))
            _loginState.value = true
    }

    fun getUserData(): User {
        return MyApplication.userdata.getString(PREF_KEY)
    }

    fun checkInvalidLogin(inputId: String, inputPw: String, userData: User): Boolean {
        when {
            inputId.isBlank() -> return false
            inputPw.isBlank() -> return false
            inputId == userData.id && inputPw == userData.pw -> {
                MyApplication.userdata.setBoolean(LOGIN_STATE_KEY, true)
                loginStateChange()
                return true
            }

            else -> return false
        }

    }

    companion object {
        private const val PREF_KEY = "userData"
        private const val LOGIN_STATE_KEY = "true"
    }
}