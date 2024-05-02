package com.sopt.now.presentation.signin

sealed interface SignInState {
    data object Empty : SignInState
    data class Success(
        val msg: Int
    ) : SignInState

    data class Failure(
        val msg: Int
    ) : SignInState
}