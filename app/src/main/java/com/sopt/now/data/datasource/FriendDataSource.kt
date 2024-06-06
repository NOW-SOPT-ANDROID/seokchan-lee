package com.sopt.now.data.datasource

import com.sopt.now.data.model.local.FriendEntity

interface FriendDataSource {
    suspend fun insertFriend(friendEntity: FriendEntity)
    suspend fun getFriend(): List<FriendEntity>
    suspend fun deleteFriend(id:Int)
    suspend fun deleteAll()
}