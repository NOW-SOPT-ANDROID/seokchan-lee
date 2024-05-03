package com.sopt.now.compose.screen.home.Search

sealed interface SearchPageState {
    data object Empty : SearchPageState
    data class Success(
        val msg: Int
    ) : SearchPageState

    data class Failure(
        val msg: Int
    ) : SearchPageState
}