package com.sopt.now.presentation.main.friends

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.data.database.FriendDatabase
import com.sopt.now.data.model.Friend
import com.sopt.now.data.model.FriendEntity
import kotlinx.coroutines.launch

class FriendViewModel(private val friendDatabase: FriendDatabase) : ViewModel() {

    fun getFriend() = viewModelScope.launch {
        friendDatabase.friendDao().getFriend()
    }

    fun insertFriend(friend: Friend) = viewModelScope.launch {
        friendDatabase.friendDao().insertFriend(FriendEntity.toFriendEntity(friend))
    }
}