package com.sopt.now.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.presentation.main.MainActivity
import com.sopt.now.presentation.signup.SignupActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewmodel by viewModels<LoginViewModel>()

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
        binding.etLoginInputId.doAfterTextChanged {
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
            Intent(this, SignupActivity::class.java).let {
                startActivity(it)
            }
        }
    }

    fun initLoginStateObserver() {
        viewmodel.loginState.observe(this) {
            when (it) {
                LoginState.Empty -> {
                }

                is LoginState.Failure -> {
                    Toast.makeText(this, it.msg, Toast.LENGTH_SHORT).show()
                }

                is LoginState.Success -> {
                    Toast.makeText(this, it.msg, Toast.LENGTH_SHORT).show()
                    Intent(this, MainActivity::class.java).let {
                        startActivity(it)
                    }
                }
            }
        }
    }
}