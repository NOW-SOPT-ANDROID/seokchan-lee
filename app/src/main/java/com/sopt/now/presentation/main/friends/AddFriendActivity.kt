package com.sopt.now.presentation.main.friends

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.databinding.ActivityAddFriendBinding

class AddFriendActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddFriendBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFriendBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    private fun initAddFriendBtnClickListener(){
        binding.btAddFriend.setOnClickListener{

        }
    }
}