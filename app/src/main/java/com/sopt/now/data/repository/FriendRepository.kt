package com.sopt.now.data.repository

import com.sopt.now.data.model.local.FriendEntity

interface FriendRepository {
    suspend fun insertFriend(friendEntity: FriendEntity)
    suspend fun getFriend(): Result<List<FriendEntity>>
    suspend fun deleteFriend(id:Int)
    suspend fun deleteAll()
}