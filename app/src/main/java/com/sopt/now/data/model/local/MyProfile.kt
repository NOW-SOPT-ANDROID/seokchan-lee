package com.sopt.now.data.model.local

import androidx.annotation.DrawableRes

data class MyProfile(
    @DrawableRes val profileImage: Int,
    val name: String,
    @DrawableRes val otherPerson: Int,
)
