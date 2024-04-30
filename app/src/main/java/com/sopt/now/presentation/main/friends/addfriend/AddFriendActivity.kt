package com.sopt.now.presentation.main.friends.addfriend

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.sopt.now.R
import com.sopt.now.data.database.FriendDatabase
import com.sopt.now.data.model.Friend
import com.sopt.now.data.model.FriendEntity
import com.sopt.now.databinding.ActivityAddFriendBinding
import com.sopt.now.presentation.login.LoginViewModel
import kotlinx.coroutines.launch

class AddFriendActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddFriendBinding
    private val viewmodel by viewModels<AddFriendViewModel>{AddFriendViewModelFactory(FriendDatabase.getDatabase(this))}



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFriendBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initEditTextChangedListener()
        initAddFriendBtnClickListener()
    }

    private fun initEditTextChangedListener() {
        binding.etInputFriendName.doAfterTextChanged {
            viewmodel.updateName(it.toString())
        }
        binding.etInputFriendMusic.doAfterTextChanged {
            viewmodel.updateMusic(it.toString())
        }
    }

    private fun initAddFriendBtnClickListener() {
        binding.btAddFriend.setOnClickListener {
            viewmodel.insertFriend()
            finish()
        }
    }
}