package com.sopt.now.compose.screen.home.MyPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.compose.MyApplication
import com.sopt.now.compose.R
import com.sopt.now.compose.data.model.ResponseUserInfoDto
import com.sopt.now.compose.data.model.ServicePool
import com.sopt.now.compose.screen.model.UserInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageViewModel : ViewModel() {
    private var _userInfo: MutableLiveData<UserInfo> = MutableLiveData(UserInfo("", "", ""))
    val userInfo: LiveData<UserInfo> get() = _userInfo

    private val _myPageState: MutableLiveData<MyPageState> = MutableLiveData(MyPageState.Empty)
    val myPageState: LiveData<MyPageState> get() = _myPageState

    private val authService by lazy { ServicePool.authService }


    fun getSavedUserId() {
        val id = MyApplication.userdata.getUserId(USER_ID_KEY)
        getUserInfo(id)
    }

    private fun getUserInfo(id: Int) {
        authService.getUserInfo(id).enqueue(object : Callback<ResponseUserInfoDto> {
            override fun onResponse(
                call: Call<ResponseUserInfoDto>,
                response: Response<ResponseUserInfoDto>
            ) {
                if (response.isSuccessful) {
                    val data: ResponseUserInfoDto? = response.body()
                    _userInfo.value = data?.data
                    _myPageState.value = MyPageState.Success(R.string.mypage_success)
                } else {
                    _myPageState.value = MyPageState.Failure(R.string.signup_failure_input)
                }
            }

            override fun onFailure(call: Call<ResponseUserInfoDto>, t: Throwable) {
                _myPageState.value = MyPageState.Failure(R.string.signup_server_error)
            }
        })

    }

    fun logOut() {
        MyApplication.userdata.clearUserData()
    }

    companion object {
        private const val USER_ID_KEY = "userId"
    }
}