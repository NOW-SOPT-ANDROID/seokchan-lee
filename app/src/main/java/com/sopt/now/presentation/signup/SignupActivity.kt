package com.sopt.now.presentation.signup

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.databinding.ActivitySignupBinding
import com.sopt.now.model.User


class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private val viewmodel by viewModels<SignupViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSignupBtnClickListener()
    }

    private fun initSignupBtnClickListener() {
        binding.btSignup.setOnClickListener {
            val userInfo = User(
                binding.etSignupId.text.toString(),
                binding.etSignupPw.text.toString(),
                binding.etSignupNickname.text.toString(),
                binding.etSignupMbti.text.toString()
            )
            val checkedUserInfo = viewmodel.checkInvalidSignup(userInfo)
            if (checkedUserInfo.signupState) {
                Toast.makeText(this, getString(checkedUserInfo.message), Toast.LENGTH_SHORT).show()
                viewmodel.setUserData(userInfo)
                finish()
            } else {
                Toast.makeText(this, getString(checkedUserInfo.message), Toast.LENGTH_SHORT).show()
            }
        }
    }
}