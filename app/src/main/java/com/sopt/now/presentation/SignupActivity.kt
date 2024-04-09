package com.sopt.now.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.R
import com.sopt.now.data.User
import com.sopt.now.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
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
            checkInvalidUserInfo(userInfo)

        }
    }

    private fun checkInvalidUserInfo(userInfo: User) {
        when {
            userInfo.id.isEmpty() -> MakeSnackBar(getString(R.string.signup_id_empty))
            userInfo.pw.isEmpty() -> MakeSnackBar(getString(R.string.signup_pw_empty))
            userInfo.nickname.isEmpty() -> MakeSnackBar(getString(R.string.signup_nickname_empty))
            userInfo.mbti.isEmpty() -> MakeSnackBar(getString(R.string.signup_mbti_empty))
            userInfo.id.length !in 6..10 -> MakeSnackBar(getString(R.string.signup_id_length))
            userInfo.pw.length !in 8..12 -> MakeSnackBar(getString(R.string.signup_pw_length))
            else -> {
                intent.putExtra("user", userInfo)
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }

    private fun MakeSnackBar(text: String) {
        Snackbar.make(
            binding.root,
            text,
            Snackbar.LENGTH_SHORT
        ).show()
    }
}