package org.sopt.and.domain.entity

import org.sopt.and.data.service.model.request.SignInRequest
import org.sopt.and.data.service.model.request.SignUpRequest


data class User (
    val username: String,
    val password: String,
    val hobby: String
) {
    fun toSignInRequest() = SignInRequest(
        username = username,
        password = password
    )

    fun toSignUpRequest() = SignUpRequest(
        userName = username,
        password = password,
        hobby = hobby
    )
}
