package com.sopt.now.compose.screen.signin

import androidx.lifecycle.ViewModel
import com.sopt.now.compose.MyApplication
import com.sopt.now.compose.screen.model.User

class SignInViewModel : ViewModel() {


    fun checkInvalidSignIn(id:String, pw:String): Boolean{
        val user = getUSer()
        when {
            id.isBlank() -> return false
            pw.isBlank() -> return false
            id == user.id && pw == user.pw -> {
                return true
            }

            else -> return false
        }
    }
    fun getUSer(): User{
        return MyApplication.userdata.getString(PREF_KEY)
    }

    companion object {
        private const val PREF_KEY = "USER_DATA"
    }
}