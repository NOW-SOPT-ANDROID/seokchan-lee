package com.sopt.now.compose.screen.home.MyPage

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sopt.now.compose.R

@Composable
fun MyPageScreen(
    viewmodel: MyPageViewModel,
    navController: NavController
) {
    viewmodel.getSavedUserId()
    var userId by remember { mutableStateOf("") }
    var userNickname by remember { mutableStateOf("") }
    var userPhone by remember { mutableStateOf("") }

    //이거 어째해요
    when (val myPageState = viewmodel.myPageState.observeAsState().value) {
        MyPageState.Empty -> {}
        is MyPageState.Failure -> {
            Toast.makeText(navController.context, myPageState.msg, Toast.LENGTH_SHORT).show()
        }

        is MyPageState.Success -> {
            Toast.makeText(navController.context, myPageState.msg, Toast.LENGTH_SHORT).show()
            userId = viewmodel.userInfo.value?.authenticationId.toString()
            userNickname = viewmodel.userInfo.value?.nickname.toString()
            userPhone = viewmodel.userInfo.value?.phone.toString()
        }

        null -> TODO()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
    ) {
        Spacer(
            modifier = Modifier
                .height(30.dp)
        )
        Text(
            text = userId,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontSize = 30.sp
        )
        Spacer(
            modifier = Modifier
                .height(30.dp)
        )
        Text(
            text = userNickname,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontSize = 30.sp
        )
        Spacer(
            modifier = Modifier
                .height(30.dp)
        )
        Text(
            text = userPhone,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontSize = 30.sp
        )
        Spacer(
            modifier = Modifier
                .height(30.dp)
        )
        Button(
            onClick = {
                viewmodel.logOut()
            }, modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        ) {

            Text(text = stringResource(R.string.mypage_logout_btn))

        }
    }
}