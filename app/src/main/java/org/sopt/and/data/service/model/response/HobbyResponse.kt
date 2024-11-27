package org.sopt.and.data.service.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class HobbyResponse(
    @SerialName("hobby") val hobby: String
)
