package org.sopt.and.data.mapper

import org.sopt.and.data.service.model.request.HobbyModifyRequest
import org.sopt.and.data.service.model.request.SignInRequest
import org.sopt.and.data.service.model.request.SignUpRequest
import org.sopt.and.domain.entity.User
import org.sopt.and.domain.entity.UserHobby

fun User.toSignInRequest(): SignInRequest = SignInRequest(
    username = username,
    password = password
)

fun User.toSignUpRequest(): SignUpRequest = SignUpRequest(
    userName = username,
    password = password,
    hobby = hobby
)

fun UserHobby.toHobbyModifyRequest() = HobbyModifyRequest(
    hobby = hobby,
    password = password
)