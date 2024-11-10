package org.sopt.and.data.service.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.and.domain.entity.UserId

@Serializable
data class SignUpResponse(
    @SerialName("no") val userId: Int
) {
    fun toId() = UserId(
        id = userId
    )
}
