package com.sopt.now.presentation.main.friends.addfriend

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.sopt.now.data.database.FriendDatabase
import com.sopt.now.databinding.ActivityAddFriendBinding

class AddFriendActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddFriendBinding
    private val viewmodel by viewModels<AddFriendViewModel> {
        AddFriendViewModelFactory(
            FriendDatabase.getDatabase(this)
        )
    }


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