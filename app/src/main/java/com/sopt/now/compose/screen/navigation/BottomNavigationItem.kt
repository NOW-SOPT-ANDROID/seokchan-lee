package com.sopt.now.compose.screen.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val label: String = "",
    val icon: ImageVector = Icons.Filled.Home,
    val route: String = ""
) {
    fun bottomNavigationItems(): List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "friend",
                icon = Icons.Filled.Person,
                route = Screen.FriendScreen.route
            ),
            BottomNavigationItem(
                label = "mypage",
                icon = Icons.Filled.Home,
                route = Screen.MyPageScreen.route
            ),
            BottomNavigationItem(
                label = "search",
                icon = Icons.Filled.Search,
                route = Screen.SearchScreen.route
            ),
        )
    }
}