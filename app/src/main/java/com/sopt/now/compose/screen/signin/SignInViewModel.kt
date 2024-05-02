package com.sopt.now.compose.screen.signin

import androidx.lifecycle.ViewModel
import com.sopt.now.compose.MyApplication
import com.sopt.now.compose.screen.model.User

class SignInViewModel : ViewModel() {
    private val user = getUSer()

    fun checkInvalidSignIn(id: String, pw: String): Boolean = id == user.id && pw == user.pw
    fun getUSer(): User = MyApplication.userdata.getString(PREF_KEY)

    companion object {
        private const val PREF_KEY = "USER_DATA"
    }
}