package com.sopt.now.data.model.local

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "friend_table")
data class FriendEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @DrawableRes val profileImage: Int,
    val name: String,
    val music: String
) {
    companion object {
        fun toFriendEntity(friend: Friend) = run {
            FriendEntity(
                id = null,
                profileImage = friend.profileImage,
                name = friend.name,
                music = friend.music

            )
        }

        fun toFriend(friendEntityList:List<FriendEntity>) = friendEntityList.map { data->
            Friend(
                id = data.id,
                profileImage = data.profileImage,
                name = data.name,
                music = data.music
            )
        }
    }
}
