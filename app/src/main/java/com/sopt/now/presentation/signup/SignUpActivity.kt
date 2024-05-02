package com.sopt.now.presentation.signup

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.sopt.now.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private val viewmodel by viewModels<SignUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initEditTextChangedListener()
        initSignupBtnClickListener()
        initSignUpStateObserver()
    }

    private fun initEditTextChangedListener() {
        binding.etSignupId.doAfterTextChanged {
            viewmodel.updateId(it.toString())
        }
        binding.etSignupPw.doAfterTextChanged {
            viewmodel.updatePw(it.toString())
        }
        binding.etSignupNickname.doAfterTextChanged {
            viewmodel.updateNickName(it.toString())
        }
        binding.etSignupMbti.doAfterTextChanged {
            viewmodel.updateMbti(it.toString())
        }
    }

    private fun initSignupBtnClickListener() {
        binding.btSignup.setOnClickListener {
            viewmodel.checkInvalidSignup()
        }
    }

    fun initSignUpStateObserver() {
        viewmodel.signupState.observe(this) {
            when (it) {
                SignUpState.Empty -> {
                }

                is SignUpState.Failure -> {
                    Toast.makeText(this, it.msg, Toast.LENGTH_SHORT).show()
                }

                is SignUpState.Success -> {
                    Toast.makeText(this, it.msg, Toast.LENGTH_SHORT).show()
                    viewmodel.setUserData()
                    finish()
                }
            }
        }
    }
}