package com.sopt.now.data.model.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class Support(
    val text: String,
    val url: String
)