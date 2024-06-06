package com.sopt.now.presentation.main.friends.addfriend

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.R
import com.sopt.now.data.model.local.FriendEntity
import com.sopt.now.data.repository.FriendRepository
import com.sopt.now.presentation.model.Friend
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddFriendViewModel @Inject constructor(
    private val friendRepository: FriendRepository
) :
    ViewModel() {
    private val name: MutableLiveData<String> = MutableLiveData("")
    private val music: MutableLiveData<String> = MutableLiveData("")

    fun insertFriend() = viewModelScope.launch {
        val friend = Friend(
            id = null,
            R.drawable.baseline_person_24,
            name = name.value.toString(),
            music = music.value.toString()
        )
        friendRepository.insertFriend(FriendEntity.toFriendEntity(friend))
    }

    fun updateName(msg: String) {
        name.value = msg
    }

    fun updateMusic(msg: String) {
        music.value = msg
    }
}