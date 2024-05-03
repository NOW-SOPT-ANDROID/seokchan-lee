package com.sopt.now.compose.screen.home.MyPage

sealed interface MyPageState {
    data object Empty : MyPageState
    data class Success(
        val msg: Int
    ) : MyPageState

    data class Failure(
        val msg: Int
    ) : MyPageState
}