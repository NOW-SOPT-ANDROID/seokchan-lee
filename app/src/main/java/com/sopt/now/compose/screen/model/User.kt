package com.sopt.now.compose.screen.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String ="",
    val pw: String ="",
    val nickname: String ="",
    val mbti: String =""
) : Parcelable