package com.sopt.now.compose.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("avatar")
    val avatar: String,
    @SerialName("email")
    val email: String,
    @SerialName("first_name")
    val first_name: String,
    @SerialName("id")
    val id: Int,
    @SerialName("last_name")
    val last_name: String
)