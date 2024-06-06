package com.sopt.now.presentation.signup

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.R
import com.sopt.now.data.datasourceImpl.SharedPrefDataSourceImpl
import com.sopt.now.data.model.remote.request.RequestSignUpDto
import com.sopt.now.data.model.remote.response.ResponseSignUpDto
import com.sopt.now.data.ServicePool
import com.sopt.now.data.repository.SharedPrefRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val sharedPreferences: SharedPrefRepository) :
    ViewModel() {
    private val _signUpState: MutableLiveData<SignUpState> = MutableLiveData(SignUpState.Empty)
    val signupState: MutableLiveData<SignUpState> get() = _signUpState

    private val id: MutableLiveData<String> = MutableLiveData("")
    private val pw: MutableLiveData<String> = MutableLiveData("")
    private val nickname: MutableLiveData<String> = MutableLiveData("")
    private val phoneNumber: MutableLiveData<String> = MutableLiveData("")

    private val authService by lazy { ServicePool.authService }

    fun signUp() {
        val signUpRequest = getSignUpRequestDto()
        authService.signUp(signUpRequest).enqueue(object : Callback<ResponseSignUpDto> {
            override fun onResponse(
                call: Call<ResponseSignUpDto>,
                response: Response<ResponseSignUpDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseSignUpDto? = response.body()
                    val userId = response.headers()["location"]
                    Log.d("SignUp", "data: $data, userId: $userId")
                    //data: ResponseSignUpDto(code=201, message=회원가입이 완료되었습니다.), userId: 307
                    _signUpState.value = SignUpState.Success(R.string.login_signup_success)
                } else {
                    _signUpState.value = SignUpState.Failure(R.string.signup_server_error)
                }
            }

            override fun onFailure(
                call: Call<ResponseSignUpDto>,
                t: Throwable
            ) {
                _signUpState.value = SignUpState.Failure(R.string.signup_server_error)
            }
        })
    }

    private fun getSignUpRequestDto(): RequestSignUpDto {
        return RequestSignUpDto(
            authenticationId = id.value.toString(),
            password = pw.value.toString(),
            nickname = nickname.value.toString(),
            phone = phoneNumber.value.toString()
        )
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

    fun updatePhoneNumber(msg: String) {
        phoneNumber.value = msg
    }
}