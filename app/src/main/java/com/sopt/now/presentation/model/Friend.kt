package com.sopt.now.presentation.model

import androidx.annotation.DrawableRes
import com.sopt.now.data.model.local.FriendEntity

data class Friend(
    val id: Int?,
    @DrawableRes val profileImage: Int,
    val name: String = "",
    val music: String = ""
) {
    companion object {
        fun toFriend(friendList: List<FriendEntity>) = friendList.map { data ->
            Friend(
                id = data.id,
                profileImage = data.profileImage,
                name = data.name,
                music = data.music
            )

        }
    }
}
