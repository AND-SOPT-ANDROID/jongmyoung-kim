package org.sopt.and.data.service.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class HobbyModifyRequest(
    @SerialName("hobby") val hobby: String,
    @SerialName("password") val password: String
)
