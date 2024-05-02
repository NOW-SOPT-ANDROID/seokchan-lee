package com.sopt.now.presentation.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.R
import com.sopt.now.data.SharedPreferenceUtil
import com.sopt.now.data.model.remote.request.RequestSignInDto
import com.sopt.now.data.model.remote.response.ResponseSignInDto
import com.sopt.now.data.ServicePool
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val sharedPreferences: SharedPreferenceUtil) :
    ViewModel() {
    private val _loginState: MutableLiveData<SignInState> = MutableLiveData(SignInState.Empty)
    val loginState: LiveData<SignInState> get() = _loginState

    private val id: MutableLiveData<String> = MutableLiveData("")
    private val pw: MutableLiveData<String> = MutableLiveData("")

    private val authService by lazy { ServicePool.authService }

    init {
        loginStateChange()
    }

    fun signIn() {
        val signInRequest = getSignInRequestDto()
        authService.signIn(signInRequest).enqueue(object : Callback<ResponseSignInDto> {
            override fun onResponse(
                call: Call<ResponseSignInDto>,
                response: Response<ResponseSignInDto>,
            ) {
                if (response.isSuccessful) {
                    val userId = response.headers()["location"]
                    if (userId != null) {
                        sharedPreferences.setUserId(USER_ID_KEY, userId.toInt())
                    }
                    sharedPreferences.setBoolean(LOGIN_STATE_KEY, true)
                    loginStateChange()
                } else {
                    _loginState.value =
                        SignInState.Failure(R.string.signup_server_error)
                }
            }

            override fun onFailure(
                call: Call<ResponseSignInDto>, t: Throwable
            ) {
                _loginState.value =
                    SignInState.Failure(R.string.signup_server_error)
            }
        })
    }

    private fun getSignInRequestDto(): RequestSignInDto {
        return RequestSignInDto(
            authenticationId = id.value.toString(),
            password = pw.value.toString()
        )
    }

    private fun loginStateChange() {
        if (sharedPreferences.getBoolean(LOGIN_STATE_KEY)) {
            _loginState.value = SignInState.Success(R.string.login_success_login)
        }
    }

    fun updateId(msg: String) {
        id.value = msg
    }

    fun updatePw(msg: String) {
        pw.value = msg
    }

    companion object {
        private const val LOGIN_STATE_KEY = "autoLogin"
        private const val USER_ID_KEY = "userInfo"
    }
}