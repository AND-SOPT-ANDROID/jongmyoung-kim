package org.sopt.and.data.service.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.and.domain.entity.UserHobby


@Serializable
data class HobbyResponse(
    @SerialName("hobby") val hobby: String
) {
    fun toUserHobby() = UserHobby(
        hobby = hobby,
        password = ""
    )
}
