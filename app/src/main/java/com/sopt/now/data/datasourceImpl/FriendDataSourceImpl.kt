package com.sopt.now.data.datasourceImpl

import com.sopt.now.data.database.FriendDatabase
import com.sopt.now.data.datasource.FriendDataSource
import com.sopt.now.data.model.local.FriendEntity
import javax.inject.Inject

class FriendDataSourceImpl @Inject constructor(
    private val friendDatabase: FriendDatabase
) : FriendDataSource {
    override suspend fun insertFriend(friendEntity: FriendEntity) {
        friendDatabase.friendDao().insertFriend(friendEntity)
    }

    override suspend fun getFriend(): List<FriendEntity> {
        return friendDatabase.friendDao().getFriend()
    }

    override suspend fun deleteFriend(id: Int) {
        friendDatabase.friendDao().deleteFriend(id)
    }

    override suspend fun deleteAll() {
        friendDatabase.friendDao().deleteAll()
    }

}