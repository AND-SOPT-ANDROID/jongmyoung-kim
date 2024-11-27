package org.sopt.and.domain.repository

import org.sopt.and.domain.entity.UserHobby

interface MyPageRepository {

    suspend fun getMyHobby(): Result<UserHobby>

    suspend fun getOtherHobby(no: String): Result<UserHobby>

    suspend fun modifyMyHobby(userHobby: UserHobby): Result<Unit>
}