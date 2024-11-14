package org.sopt.and.domain.repository

import org.sopt.and.data.service.model.request.HobbyModifyRequest
import org.sopt.and.domain.entity.UserHobby

interface MyPageRepository {

    suspend fun getMyHobby(): Result<UserHobby>

    suspend fun getOtherHobby(): Result<UserHobby>

    suspend fun modifyMyHobby(hobbyModifyRequest: HobbyModifyRequest): Result<Unit>
}