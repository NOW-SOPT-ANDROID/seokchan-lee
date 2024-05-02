package com.sopt.now.presentation.main.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.data.PreferenceUtil
import com.sopt.now.data.ResponseUserInfoDto
import com.sopt.now.data.ServicePool
import com.sopt.now.presentation.model.UserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(private val sharedPreferences: PreferenceUtil) :
    ViewModel() {
    private var _userInfo: MutableLiveData<UserInfo> = MutableLiveData(UserInfo("", "", ""))
    val userInfo: LiveData<UserInfo> get() = _userInfo

    private val authService by lazy { ServicePool.authService }

    fun getSavedUserId() {
        val id = sharedPreferences.getUserId(USER_ID_KEY)
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
                } else {
                    Log.d("userInfo", "오류")
                }
            }

            override fun onFailure(call: Call<ResponseUserInfoDto>, t: Throwable) {
                Log.d("userInfo", "서버 오류")
            }
        })
    }

    fun logOut() {
        sharedPreferences.clearUserData()
    }

    companion object {
        private const val USER_ID_KEY = "userInfo"
    }
}