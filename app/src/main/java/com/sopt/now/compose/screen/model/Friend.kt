package com.sopt.now.compose.screen.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector

data class Friend(
    val profileImage: ImageVector,
    val name: String = "",
    val music:String = ""
)
