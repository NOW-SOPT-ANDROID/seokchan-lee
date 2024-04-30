package com.sopt.now.presentation.main.friends

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sopt.now.data.database.FriendDatabase
import com.sopt.now.presentation.main.friends.addfriend.AddFriendViewModel
import java.lang.IllegalArgumentException

class FriendViewModelFactory(private val friendDatabase: FriendDatabase): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddFriendViewModel::class.java)){
            return AddFriendViewModel(friendDatabase) as T
        }else{
            throw IllegalArgumentException("unknown")
        }
    }
}