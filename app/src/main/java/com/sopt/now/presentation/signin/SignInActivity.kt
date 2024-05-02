package com.sopt.now.presentation.signin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.presentation.main.MainActivity
import com.sopt.now.presentation.signup.SignUpActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewmodel by viewModels<SignInViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initEditTextChangedListener()
        initLoginStateObserver()
        initLoginBtnClickListener()
        initSignupBtnClickListener()
    }

    private fun initEditTextChangedListener() {
        binding.etLoginInputId.doAfterTextChanged {
            viewmodel.updateId(it.toString())
        }
        binding.etLoginInputPw.doAfterTextChanged {
            viewmodel.updatePw(it.toString())
        }
    }

    private fun initLoginBtnClickListener() {
        binding.btLogin.setOnClickListener {
            viewmodel.checkInvalidLogin()
        }
    }

    private fun initSignupBtnClickListener() {
        binding.btSignup.setOnClickListener {
            Intent(this, SignUpActivity::class.java).let {
                startActivity(it)
            }
        }
    }

    private fun initLoginStateObserver() {
        viewmodel.loginState.observe(this) {
            when (it) {
                SignInState.Empty -> {
                    Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show()
                }

                is SignInState.Failure -> {
                    Toast.makeText(this, it.msg, Toast.LENGTH_SHORT).show()
                }

                is SignInState.Success -> {
                    Toast.makeText(this, it.msg, Toast.LENGTH_SHORT).show()
                    Intent(this, MainActivity::class.java).let {
                        startActivity(it)
                    }
                }
            }
        }
    }
}