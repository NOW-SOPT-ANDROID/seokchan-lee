package com.sopt.now.data.model.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseReqresDto(
    @SerialName("data")
    val `data`: List<Data>,
    @SerialName("page")
    val page: Int,
    @SerialName("per_page")
    val per_page: Int,
    @SerialName("support")
    val support: Support,
    @SerialName("total")
    val total: Int,
    @SerialName("total_pages")
    val total_pages: Int
)