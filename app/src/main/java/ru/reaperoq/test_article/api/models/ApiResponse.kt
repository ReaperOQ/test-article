package ru.reaperoq.test_article.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    @SerialName("retCode")
    val code: Int,

    @SerialName("retMsg")
    val message: String,

    val result: T?
)
