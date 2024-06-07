package com.sopt.now.compose.screen.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.compose.MyApplication
import com.sopt.now.compose.R
import com.sopt.now.compose.data.model.RequestSignInDto
import com.sopt.now.compose.data.model.ResponseSignInDto
import com.sopt.now.compose.data.model.ServicePool
import com.sopt.now.compose.data.repository.SharedPrefRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val sharedPreferences: SharedPrefRepository
) : ViewModel() {
    private val _loginState: MutableLiveData<SignInState> = MutableLiveData(SignInState.Empty)
    val loginState: LiveData<SignInState> get() = _loginState

    private val id: MutableLiveData<String> = MutableLiveData("")
    private val pw: MutableLiveData<String> = MutableLiveData("")
    private val authService by lazy { ServicePool.authService }

    fun updateLoginInfo(userId: String, userPw: String) {
        id.value = userId
        pw.value = userPw
    }

    fun signIn() {
        val signInRequest = getSignInRequestDto()
        authService.signIn(signInRequest).enqueue(object : Callback<ResponseSignInDto> {
            override fun onResponse(
                call: Call<ResponseSignInDto>,
                response: Response<ResponseSignInDto>
            ) {
                if (response.isSuccessful) {
                    val userId = response.headers()["location"]
                    if (userId != null) {
                        MyApplication.userdata.setUserId(USER_ID_KEY, userId.toInt())
                    }
                    _loginState.value = SignInState.Success(R.string.login_screen_success_login)
                } else {
                    _loginState.value = SignInState.Failure(R.string.signup_failure_input)
                }
            }

            override fun onFailure(call: Call<ResponseSignInDto>, t: Throwable) {
                _loginState.value = SignInState.Failure(R.string.signup_server_error)
            }
        })
    }

    private fun getSignInRequestDto(): RequestSignInDto {
        return RequestSignInDto(
            authenticationId = id.value.toString(),
            password = pw.value.toString()
        )
    }

    companion object {
        private const val USER_ID_KEY = "userId"
    }
}