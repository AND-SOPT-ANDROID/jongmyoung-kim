package org.sopt.and.data.service.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.and.domain.entity.Token


@Serializable
data class SignInResponse(
    @SerialName("token") val accessToken: String
)
