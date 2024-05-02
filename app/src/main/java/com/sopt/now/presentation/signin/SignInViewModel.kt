package com.sopt.now.presentation.signin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.MyApplication
import com.sopt.now.R
import com.sopt.now.data.PreferenceUtil
import com.sopt.now.presentation.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val sharedPreferences: PreferenceUtil) :
    ViewModel() {
    private val _loginState: MutableLiveData<SignInState> = MutableLiveData(SignInState.Empty)
    val loginState: LiveData<SignInState> get() = _loginState

    private val id: MutableLiveData<String> = MutableLiveData("")
    private val pw: MutableLiveData<String> = MutableLiveData("")

    init {
        loginStateChange()
    }

    private fun loginStateChange() {
        if (sharedPreferences.getBoolean(LOGIN_STATE_KEY)) {
            _loginState.value = SignInState.Success(R.string.login_success_login)
            Log.d("asd", _loginState.value.toString() + "   " + loginState.value.toString())
        }
    }

    private fun getUserData(): User {
        return sharedPreferences.getString(PREF_KEY)
    }

    fun checkInvalidLogin() {
        val userData = getUserData()
        when {
            id.value.toString().isBlank() -> _loginState.value =
                SignInState.Failure(R.string.signup_empty_id)

            pw.value.toString().isBlank() -> _loginState.value =
                SignInState.Failure(R.string.signup_empty_pw)

            id.value.toString() == userData.id && pw.value.toString() == userData.pw -> {
                sharedPreferences.setBoolean(LOGIN_STATE_KEY, true)
                loginStateChange()
            }

            else -> _loginState.value = SignInState.Empty
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