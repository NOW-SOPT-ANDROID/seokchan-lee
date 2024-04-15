package com.sopt.now.compose.screen.navigation

sealed class Screen(
    val route: String,
) {
    object FriendScreen : Screen("friend")
    object MyPageScreen : Screen("myPage")
    object SearchScreen : Screen("search")
}