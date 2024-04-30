package com.sopt.now.data.model

import androidx.annotation.DrawableRes

data class MyProfile(
    @DrawableRes val profileImage: Int,
    val name: String,
    @DrawableRes val otherPerson: Int,
)
