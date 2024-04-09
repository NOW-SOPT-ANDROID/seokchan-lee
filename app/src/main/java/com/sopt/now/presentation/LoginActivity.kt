package com.sopt.now.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.R
import com.sopt.now.data.User
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.util.getParcelable

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initResultLauncher()
        initSignupBtnClickListener()
    }

    private fun initResultLauncher() {
        resultLauncher =
            registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) { result ->
                if (result.resultCode == RESULT_OK) {
                    val userInfo = result.data?.getParcelable("user", User::class.java)
                    Snackbar.make(
                        binding.root,
                        getString(R.string.login_signup_success), Snackbar.LENGTH_SHORT
                    ).show()
                    initLoginBtnClickListener(userInfo)
                }
            }
    }

    private fun initLoginBtnClickListener(user: User?) {
        binding.btLogin.setOnClickListener {
            if (user?.id == binding.etLoginInputId.text.toString() && user.pw == binding.etLoginInputPw.text.toString()) {
                Toast.makeText(this, getString(R.string.login_success_login), Toast.LENGTH_SHORT)
                    .show()
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("user", user)
                startActivity(intent)
            }
        }
    }

    private fun initSignupBtnClickListener() {
        binding.btSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            resultLauncher.launch(intent)
        }
    }
}