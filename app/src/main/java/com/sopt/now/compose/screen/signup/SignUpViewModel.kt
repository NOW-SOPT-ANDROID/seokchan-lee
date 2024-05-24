package com.sopt.now.compose.screen.signup

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.compose.R
import com.sopt.now.compose.data.model.RequestSignUpDto
import com.sopt.now.compose.data.model.ResponseSignUpDto
import com.sopt.now.compose.data.model.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    private val _signUpState: MutableLiveData<SignUpState> = MutableLiveData(SignUpState.Empty)
    val signupState: MutableLiveData<SignUpState> get() = _signUpState

    private val id: MutableLiveData<String> = MutableLiveData("")
    private val pw: MutableLiveData<String> = MutableLiveData("")
    private val nickname: MutableLiveData<String> = MutableLiveData("")
    private val phone: MutableLiveData<String> = MutableLiveData("")

    private val authService by lazy { ServicePool.authService }

    fun updateUserInfo(userId: String, userPw: String, userNickname: String, userPhone: String) {
        id.value = userId
        pw.value = userPw
        nickname.value = userNickname
        phone.value = userPhone
    }

    fun signUp() {
        val signUpRequest = getSignUpRequestDto()
        authService.signUp(signUpRequest).enqueue(object : Callback<ResponseSignUpDto> {
            override fun onResponse(
                call: Call<ResponseSignUpDto>,
                response: Response<ResponseSignUpDto>
            ) {
                if (response.isSuccessful) {
                    val data: ResponseSignUpDto? = response.body()
                    val userId = response.headers()["location"]
                    Log.d("SignUp", "data: $data, userId: $userId")
                    //data: ResponseSignUpDto(code=201, message=회원가입이 완료되었습니다.), userId: 485
                    _signUpState.value = SignUpState.Success(R.string.login_screen_success_signup)
                } else {
                    _signUpState.value = SignUpState.Failure(R.string.signup_failure_input)
                }
            }

            override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                _signUpState.value = SignUpState.Failure(R.string.signup_server_error)
            }
        })
    }

    private fun getSignUpRequestDto(): RequestSignUpDto {
        return RequestSignUpDto(
            authenticationId = id.value.toString(),
            password = pw.value.toString(),
            nickname = nickname.value.toString(),
            phone = phone.value.toString()
        )
    }
}
