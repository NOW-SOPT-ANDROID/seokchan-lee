package com.sopt.now.compose.screen.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.sopt.now.compose.screen.navigation.NavigationController

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavigationController(navController = navController)
}