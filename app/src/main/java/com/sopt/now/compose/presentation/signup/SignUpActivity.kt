package com.sopt.now.compose.presentation.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.R
import com.sopt.now.compose.presentation.User
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme
import com.sopt.now.compose.util.makeToastMessage

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SignUpScreen()
                }
            }
        }
    }

    @Composable
    fun SignUpScreen() {
        var userId by remember { mutableStateOf("") }
        var userPw by remember { mutableStateOf("") }
        var userNickname by remember { mutableStateOf("") }
        var userMBTI by remember { mutableStateOf("") }

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
                text = stringResource(R.string.signup_screen_title),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                fontSize = 30.sp
            )

            Spacer(
                modifier = Modifier
                    .height(50.dp)
            )
            Column {
                Text(stringResource(R.string.signup_screen_id))
                TextField(
                    value = userId,
                    onValueChange = { userId = it },
                    placeholder = { Text(stringResource(R.string.signup_screen_input_title)) },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(
                modifier = Modifier
                    .height(30.dp)
            )
            Column {
                Text(stringResource(R.string.signup_screen_pw))
                TextField(
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation(),
                    value = userPw,
                    onValueChange = { userPw = it },
                    placeholder = { Text(stringResource(R.string.signup_screen_input_pw)) },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(
                modifier = Modifier
                    .height(30.dp)
            )
            Column {
                Text(stringResource(R.string.signup_screen_nickname))
                TextField(
                    value = userNickname,
                    onValueChange = { userNickname = it },
                    placeholder = { Text(stringResource(R.string.signup_screen_input_nickname)) },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(
                modifier = Modifier
                    .height(30.dp)
            )
            Column {
                Text(stringResource(R.string.signup_screen_mbti))
                TextField(
                    value = userMBTI,
                    onValueChange = { userMBTI = it },
                    placeholder = { Text(stringResource(R.string.signup_screen_input_mbti)) },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(
                modifier = Modifier
                    .height(100.dp)
            )
            Button(
                onClick = {
                    User(
                        userId,
                        userPw,
                        userNickname,
                        userMBTI
                    ).also {
                        checkInvalidUserInfo(it)
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.signup_screen_btn))
            }
            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun SignInPreview2() {
        NOWSOPTAndroidTheme {
            SignUpScreen()
        }
    }

    fun checkInvalidUserInfo(userInfo: User) {
        when {
            userInfo.id.isEmpty() -> makeToastMessage(
                applicationContext,
                getString(R.string.signup_screen_empty_id)
            )

            userInfo.pw.isEmpty() -> makeToastMessage(
                applicationContext,
                getString(R.string.signup_screen_empty_pw)
            )

            userInfo.nickname.isEmpty() -> makeToastMessage(
                applicationContext,
                getString(R.string.signup_screen_empty_nickname)
            )

            userInfo.mbti.isEmpty() -> makeToastMessage(
                applicationContext,
                getString(R.string.signup_screen_empty_mbti)
            )

            userInfo.id.length !in 6..10 -> makeToastMessage(
                applicationContext,
                getString(R.string.signup_screen_invalid_id)
            )

            userInfo.pw.length !in 8..12 -> makeToastMessage(
                applicationContext,
                getString(R.string.signup_screen_invalid_pw)
            )

            else -> {
                intent.putExtra(USER_KEY, userInfo).apply {
                    setResult(RESULT_OK, intent)
                }.let {
                    finish()
                }
            }
        }
    }


    companion object {
        private const val USER_KEY = "user"
    }
}


