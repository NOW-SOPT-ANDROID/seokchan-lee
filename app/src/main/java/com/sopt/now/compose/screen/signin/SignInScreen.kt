package com.sopt.now.compose.screen.signin

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
import androidx.compose.runtime.livedata.observeAsState
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
fun SignInScreen(
    viewmodel: SignInViewModel,
    navController: NavController
) {
    InitSignInStateObserver(viewmodel, navController)
    var userId by remember { mutableStateOf("") }
    var userPw by remember { mutableStateOf("") }

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
            text = stringResource(R.string.login_screen_title),
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontSize = 30.sp
        )

        Spacer(
            modifier = Modifier
                .height(50.dp)
        )
        Column {
            Text(stringResource(R.string.login_screen_id))
            TextField(
                value = userId,
                onValueChange = { userId = it },
                placeholder = { Text(stringResource(R.string.login_screen_input_id)) },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(
            modifier = Modifier
                .height(30.dp)
        )
        Column {
            Text(stringResource(R.string.login_screen_pw))
            TextField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation(),
                value = userPw,
                onValueChange = { userPw = it },
                placeholder = { Text(stringResource(R.string.login_screen_input_pw)) },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(
            modifier = Modifier
                .weight(1f)
        )
        Button(
            onClick = {
                viewmodel.updateLoginInfo(userId, userPw)
                viewmodel.signIn()
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.login_screen_login_btn))
        }

        Spacer(
            modifier = Modifier
                .height(10.dp)
        )
        Button(
            onClick = {
                navController.navigate("signUp")
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.login_screen_signup_btn))
        }
        Spacer(
            modifier = Modifier
                .height(70.dp)
        )
    }
}

@Composable
private fun InitSignInStateObserver(viewmodel: SignInViewModel, navController: NavController) {
    when (val signInState = viewmodel.loginState.observeAsState().value) {
        SignInState.Empty -> {}
        is SignInState.Failure -> {
            Toast.makeText(navController.context, signInState.msg, Toast.LENGTH_SHORT).show()
        }

        is SignInState.Success -> {
            Toast.makeText(navController.context, signInState.msg, Toast.LENGTH_SHORT).show()
            navController.navigate("home")
        }

        null -> TODO()
    }
}