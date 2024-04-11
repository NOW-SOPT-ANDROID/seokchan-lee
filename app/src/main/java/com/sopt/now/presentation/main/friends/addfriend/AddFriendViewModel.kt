package com.sopt.now.presentation.main.friends.addfriend

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.R
import com.sopt.now.data.database.FriendDatabase
import com.sopt.now.data.model.Friend
import com.sopt.now.data.model.FriendEntity
import kotlinx.coroutines.launch

class AddFriendViewModel(private val friendDatabase: FriendDatabase) : ViewModel() {
    private val name: MutableLiveData<String> = MutableLiveData("")
    private val music: MutableLiveData<String> = MutableLiveData("")

    fun insertFriend() = viewModelScope.launch {
        val friend = Friend(
            id = null,
            R.drawable.baseline_person_24,
            name = name.value.toString(),
            music = music.value.toString()
        )
        friendDatabase.friendDao().insertFriend(FriendEntity.toFriendEntity(friend))
    }

    fun updateName(msg: String) {
        name.value = msg
    }

    fun updateMusic(msg: String) {
        music.value = msg
    }
}