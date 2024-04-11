package com.sopt.now.presentation.main.friends.addfriend

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.sopt.now.R
import com.sopt.now.data.database.FriendDatabase
import com.sopt.now.data.model.Friend
import com.sopt.now.data.model.FriendEntity
import com.sopt.now.databinding.ActivityAddFriendBinding
import kotlinx.coroutines.launch

class AddFriendActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddFriendBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFriendBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAddFriendBtnClickListener()
    }

    private fun initAddFriendBtnClickListener() {
        binding.btAddFriend.setOnClickListener {


            val friend = Friend(
                id = null,
                R.drawable.baseline_person_24,
                binding.etInputFriendName.text.toString(),
                binding.etInputFriendMusic.text.toString()
            )


            //TODO. 데이터 추가
            lifecycleScope.launch {
                val db = Room.databaseBuilder(
                    applicationContext,
                    FriendDatabase::class.java, "friend_database"
                ).build()
                val friendDao = db.friendDao()
                friendDao.insertFriend(FriendEntity.toFriendEntity(friend))
            }
            //TODO
            finish()
        }
    }
}