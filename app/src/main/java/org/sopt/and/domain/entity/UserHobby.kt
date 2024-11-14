package org.sopt.and.domain.entity

import org.sopt.and.data.service.model.request.HobbyModifyRequest


data class UserHobby(
    val hobby: String,
    val password: String
) {
    fun toHobbyModifyRequest() = HobbyModifyRequest(
        hobby = hobby,
        password = password
    )
}
