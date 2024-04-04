package com.sopt.now.compose.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.R
import com.sopt.now.compose.presentation.User
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme
import com.sopt.now.compose.util.getParcelable

class MainActivity : ComponentActivity() {
    private var user = User()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            getUserInfo()
            NOWSOPTAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }

    @Composable
    fun MainScreen() {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {
            Spacer(
                modifier = Modifier
                    .height(50.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_android_black_24dp),
                    contentDescription = user.nickname,
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                )
                Text(
                    text = user.nickname,
                    fontSize = 30.sp,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 10.dp)
                )
            }
            Text(
                text = stringResource(R.string.main_screen_id),
                modifier = Modifier,
                fontSize = 30.sp
            )

            Text(
                text = user.id,
                fontSize = 30.sp
            )
            Spacer(
                modifier = Modifier
                    .height(50.dp)
            )
            Text(
                text = stringResource(R.string.main_screen_pw),
                fontSize = 30.sp
            )
            Text(
                text = user.pw,
                fontSize = 30.sp
            )

        }

    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        NOWSOPTAndroidTheme {
            MainScreen()
        }
    }

    fun getUserInfo() {
        val userInfo = intent.getParcelable(USER_KEY, User::class.java)
        user = User(
            userInfo?.id.toString(),
            userInfo?.pw.toString(),
            userInfo?.nickname.toString(),
            userInfo?.mbti.toString()
        )
    }

    companion object {
        private const val USER_KEY = "user"
    }
}