package org.sopt.and.data.service.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T> (
    @SerialName("result") val result: T,
    @SerialName("code") val code: String? = null
)