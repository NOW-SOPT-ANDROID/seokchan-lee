package com.sopt.now.data.repository

import com.sopt.now.data.datasource.FriendDataSource
import com.sopt.now.data.model.local.FriendEntity
import javax.inject.Inject

class FriendRepositoryImpl @Inject constructor(
    private val friendDataSource: FriendDataSource
) : FriendRepository {
    override suspend fun insertFriend(friendEntity: FriendEntity) {
        friendDataSource.insertFriend(friendEntity)

    }

    override suspend fun getFriend(): Result<List<FriendEntity>> =
        runCatching {
            friendDataSource.getFriend()
        }


    override suspend fun deleteFriend(id: Int) {
        friendDataSource.deleteFriend(id)

    }

    override suspend fun deleteAll() {
        friendDataSource.deleteAll()

    }
}