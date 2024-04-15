package com.sopt.now.compose.screen.model

import androidx.compose.ui.graphics.vector.ImageVector

data class Profile(
    val profileImage: ImageVector,
    val name: String,
    val otherPerson: ImageVector,
)
