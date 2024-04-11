package com.sopt.now.data.model

import androidx.annotation.DrawableRes

data class Friend(
    val id:Int?,
    @DrawableRes val profileImage: Int,
    val name: String = "",
    val music:String = ""
)
