package com.sopt.now.compose.screen.home.Friends

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.sopt.now.compose.screen.model.FriendTest
import com.sopt.now.compose.screen.model.ProfileTest

@Composable
fun FriendScreen() {
    LazyColumn {
        items(ProfileTest) {
            ProfileListScreen(it)
        }
        items(FriendTest) {
            FriendListScreen(it)
        }
    }
}