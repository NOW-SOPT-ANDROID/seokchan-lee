package com.sopt.now.compose.screen.home.Friends

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.screen.model.Friend
import com.sopt.now.compose.ui.theme.Android

@Composable
fun FriendListScreen(friend: Friend) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(50.dp),
            imageVector = friend.profileImage,
            contentDescription = null,
            tint = Android
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = friend.name,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = friend.music,
            modifier = Modifier.wrapContentWidth(Alignment.End),
            fontSize = 10.sp,
        )
        Spacer(modifier = Modifier.width(20.dp))
    }
}


