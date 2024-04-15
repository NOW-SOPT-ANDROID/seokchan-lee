package com.sopt.now.compose.screen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sopt.now.compose.screen.home.HomeScreen
import com.sopt.now.compose.screen.main.MainScreen
import com.sopt.now.compose.screen.signin.SignInScreen
import com.sopt.now.compose.screen.signin.SignInViewModel
import com.sopt.now.compose.screen.signup.SignUpScreen
import com.sopt.now.compose.screen.signup.SignUpViewModel

@Composable
fun NavigationController(navController: NavHostController) {

    NavHost(navController = navController, startDestination = "signIn") {
        composable("main") {
            MainScreen()
        }
        composable("signIn") {
            SignInScreen(viewmodel = SignInViewModel(), navController = navController)
        }
        composable("signUp") {
            SignUpScreen(viewmodel = SignUpViewModel(),navController = navController)
        }
        composable("home") {
            HomeScreen()
        }
    }
}
