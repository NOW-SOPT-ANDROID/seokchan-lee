package com.sopt.now.presentation.main.friends

import androidx.annotation.DrawableRes

data class MyProfile(
    @DrawableRes val profileImage: Int,
    val name: String,
    @DrawableRes val otherPerson: Int,
)
