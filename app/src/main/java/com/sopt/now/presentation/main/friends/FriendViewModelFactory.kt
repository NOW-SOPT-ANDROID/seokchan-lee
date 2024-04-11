package com.sopt.now.presentation.main.friends

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sopt.now.data.database.FriendDatabase

class FriendViewModelFactory(private val friendDatabase: FriendDatabase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FriendViewModel::class.java)) {
            return FriendViewModel(friendDatabase) as T
        } else {
            throw IllegalArgumentException("unknown")
        }
    }
}