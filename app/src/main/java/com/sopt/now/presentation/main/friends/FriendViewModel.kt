package com.sopt.now.presentation.main.friends

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.data.database.FriendDatabase
import com.sopt.now.data.model.Friend
import com.sopt.now.data.model.FriendEntity
import kotlinx.coroutines.launch

class FriendViewModel(private val friendDatabase: FriendDatabase) : ViewModel() {
    private val _friendList = MutableLiveData<List<Friend>>()
    val friendList: LiveData<List<Friend>> = _friendList


    fun getFriend() = viewModelScope.launch {
        _friendList.value = FriendEntity.toFriend(friendDatabase.friendDao().getFriend())
    }

    fun deleteFriend(id: Int) = viewModelScope.launch {
        friendDatabase.friendDao().deleteFriend(id)
    }
}