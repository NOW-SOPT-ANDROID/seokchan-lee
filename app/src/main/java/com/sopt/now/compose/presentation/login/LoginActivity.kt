package com.sopt.now.compose.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
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
import com.sopt.now.compose.presentation.main.MainActivity
import com.sopt.now.compose.presentation.signup.SignUpActivity
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme
import com.sopt.now.compose.util.getParcelable
import com.sopt.now.compose.util.makeToastMessage

class LoginActivity : ComponentActivity() {
    private var user = User()

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen()
                }
            }
        }
        initResultLauncher()
    }

    @Composable
    fun LoginScreen() {
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
                    checkInvalidLogin(userId, userPw)
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
                    Intent(applicationContext, SignUpActivity::class.java).let {
                        resultLauncher.launch(it)
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.login_screen_signup_btn))
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun LoginPreview() {
        NOWSOPTAndroidTheme {
            LoginScreen()
        }
    }

    private fun initResultLauncher() {
        resultLauncher =
            registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) { result ->
                if (result.resultCode == RESULT_OK) {

                    result.data?.getParcelable(USER_KEY, User::class.java)?.let {
                        user = it
                    }



                    makeToastMessage(
                        applicationContext,
                        getString(R.string.login_screen_success_signup)
                    )
                }
            }
    }

    private fun checkInvalidLogin(inputId: String, inputPw: String) {
        when {
            inputId.isBlank() -> makeToastMessage(
                applicationContext,
                getString(R.string.login_screen_empty_id)
            )

            inputPw.isBlank() -> makeToastMessage(
                applicationContext,
                getString(R.string.login_screen_empty_pw)
            )

            inputId == user.id && inputPw == user.pw -> {
                makeToastMessage(applicationContext, getString(R.string.login_screen_success_login))
                val intent = Intent(applicationContext, MainActivity::class.java)
                intent.putExtra(USER_KEY, user)
                startActivity(intent)
            }

            else -> makeToastMessage(
                applicationContext,
                getString(R.string.login_screen_invalid_account)
            )
        }
    }

    companion object {
        private const val USER_KEY = "user"
    }
}

