package com.sopt.now.presentation.main.friends

import androidx.annotation.DrawableRes

data class Friend(
    @DrawableRes val profileImage: Int,
    val name: String,
    val music:String
)
