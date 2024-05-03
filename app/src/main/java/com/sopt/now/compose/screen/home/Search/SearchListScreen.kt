package com.sopt.now.compose.screen.home.Search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sopt.now.compose.screen.model.ReqresUser
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

@Composable
fun SearchListScreen(reqresUser: ReqresUser) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = reqresUser.name,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontSize = 30.sp
        )
        Text(
            text = reqresUser.email,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontSize = 30.sp
        )
        AsyncImage(
            model = reqresUser.avatar,
            contentDescription = "",
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    NOWSOPTAndroidTheme {
        SearchListScreen(ReqresUser("ads", "asd", "https://reqres.in/img/faces/7-image.jpg"))
    }
}