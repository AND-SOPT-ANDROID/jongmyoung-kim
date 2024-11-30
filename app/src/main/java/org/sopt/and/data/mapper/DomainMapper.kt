package org.sopt.and.data.mapper

import org.sopt.and.data.service.model.response.HobbyResponse
import org.sopt.and.data.service.model.response.SignInResponse
import org.sopt.and.data.service.model.response.SignUpResponse
import org.sopt.and.domain.entity.Token
import org.sopt.and.domain.entity.UserHobby
import org.sopt.and.domain.entity.UserId

fun HobbyResponse.toUserHobby() = UserHobby(
    hobby = hobby,
    password = ""
)

fun SignInResponse.toToken() = Token(
    accessToken = accessToken
)

fun SignUpResponse.toUserId() = UserId(
    id = userId
)