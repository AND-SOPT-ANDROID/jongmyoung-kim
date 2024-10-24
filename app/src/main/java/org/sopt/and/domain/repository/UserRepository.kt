package org.sopt.and.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun signIn(
        email: String,
        password: String
    ): Result<Unit>

    fun signUp(
        email: String, password: String
    ): Result<Unit>

    fun signOut(): Result<Unit>

    suspend fun getUserEmail(): Result<String>
}