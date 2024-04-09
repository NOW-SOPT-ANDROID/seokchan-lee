package com.sopt.now.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.data.User
import com.sopt.now.databinding.ActivityMainBinding
import com.sopt.now.util.getParcelable

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getUserInfo()
    }

    private fun getUserInfo() {
        val userInfo = intent.getParcelable("user", User::class.java)

        binding.tvUsername.text = userInfo?.nickname
        binding.tvUserid.text = userInfo?.id
        binding.tvUserpw.text = userInfo?.pw
    }
}