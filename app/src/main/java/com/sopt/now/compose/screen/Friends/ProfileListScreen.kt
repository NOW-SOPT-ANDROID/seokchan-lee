package com.sopt.now.compose.screen.Friends

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.screen.model.Profile
import com.sopt.now.compose.screen.model.ProfileTest
import com.sopt.now.compose.ui.theme.Android
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

@Composable
fun ProfileListScreen(profile: Profile) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(100.dp),
            imageVector = profile.profileImage,
            contentDescription = null,
            tint = Android
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = profile.name,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            modifier = Modifier
                .size(100.dp),
            imageVector = profile.otherPerson,
            contentDescription = null,
            tint = Android

        )
        Spacer(modifier = Modifier.width(20.dp))

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    NOWSOPTAndroidTheme {
        ProfileListScreen(ProfileTest.get(0))
    }
}