package com.sopt.now.presentation.login

sealed interface LoginState {
    object Empty : LoginState
    data class Success(
        val msg: Int
    ): LoginState

    data class Failure(
        val msg: Int
    ): LoginState
}