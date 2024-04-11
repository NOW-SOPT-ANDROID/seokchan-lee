package com.sopt.now.data.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FriendDao {

    @Insert
    suspend fun insertFriend(friendEntity: FriendEntity)

    @Query("select * from friend_table")
    suspend fun getFriend(): List<FriendEntity>

    @Query("delete from friend_table where id = :id")
    suspend fun deleteFriend(id:Int)

    @Query("delete from friend_table")
    suspend fun deleteAll()
}