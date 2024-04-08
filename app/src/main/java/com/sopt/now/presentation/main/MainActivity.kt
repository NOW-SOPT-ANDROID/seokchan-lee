package com.sopt.now.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.MyApplication
import com.sopt.now.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewmodel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initLogoutBtnClickListener()
        setUserData()
    }

    private fun initLogoutBtnClickListener() {
        binding.btLogout.setOnClickListener {
            MyApplication.userdata.clearUserData()
            finish()
        }
    }

    private fun setUserData() {
        viewmodel.getUserData()?.let { userInfo ->
            with(binding){
                tvUsername.text = userInfo.nickname
                tvUserid.text = userInfo.id
                tvUserpw.text = userInfo.pw
            }
        }
    }


}