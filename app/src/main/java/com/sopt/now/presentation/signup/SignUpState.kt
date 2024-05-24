package com.sopt.now.presentation.signup

sealed interface SignUpState {
    object Empty : SignUpState
    data class Success(
        val msg: Int
    ) : SignUpState

    data class Failure(
        val msg: Int
    ) : SignUpState
}
