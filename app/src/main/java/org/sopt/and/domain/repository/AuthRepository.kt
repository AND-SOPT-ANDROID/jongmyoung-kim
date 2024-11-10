package org.sopt.and.domain.repository

import org.sopt.and.domain.entity.Token
import org.sopt.and.domain.entity.User
import org.sopt.and.domain.entity.UserId


interface AuthRepository {
    suspend fun signIn(user: User): Result<Token>

    suspend fun signUp(user: User): Result<UserId>

    suspend fun signOut(): Result<Unit>

    suspend fun getUserHobby(): Result<String>
}
