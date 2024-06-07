package com.sopt.now.presentation.main.friends

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.data.model.local.FriendEntity
import com.sopt.now.data.repository.FriendRepository
import com.sopt.now.presentation.model.Friend
import com.sopt.now.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendViewModel @Inject constructor(
    private val friendRepository: FriendRepository
) :
    ViewModel() {
    private val _friendList = MutableLiveData<UiState<List<Friend>>>(UiState.Empty)
    val friendList: LiveData<UiState<List<Friend>>> get() = _friendList

    init {
        getFriend()
    }


    fun getFriend() = viewModelScope.launch {
        friendRepository.getFriend()
            .onSuccess {
                _friendList.value = UiState.Success(Friend.toFriend(it))
            }
            .onFailure {
                _friendList.value = UiState.Failure("room 에러")
            }
    }

    fun deleteFriend(id: Int) = viewModelScope.launch {
        friendRepository.deleteFriend(id)
    }
}