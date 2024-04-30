package com.sopt.now.compose.screen.signup

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sopt.now.compose.R

@Composable
fun SignUpScreen(
    viewmodel: SignUpViewModel,
    navController: NavController
) {
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
                viewmodel.updateUserInfo(userId, userPw, userNickname, userMBTI)
                val valid = viewmodel.checkInvalidSignup()
                when (valid.signup) {
                    false -> {
                        Toast.makeText(navController.context, valid.message, Toast.LENGTH_SHORT)
                            .show()
                    }

                    true -> {
                        Toast.makeText(navController.context, valid.message, Toast.LENGTH_SHORT)
                            .show()
                        navController.navigate("signIn")
                    }
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


