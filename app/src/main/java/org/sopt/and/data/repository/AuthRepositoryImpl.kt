package org.sopt.and.data.repository

import org.sopt.and.data.datasource.local.LocalPreferences
import org.sopt.and.data.datasource.remote.AuthRemoteDataSource
import org.sopt.and.data.mapper.toSignInRequest
import org.sopt.and.data.mapper.toSignUpRequest
import org.sopt.and.data.mapper.toToken
import org.sopt.and.data.mapper.toUserId
import org.sopt.and.domain.entity.Token
import org.sopt.and.domain.entity.User
import org.sopt.and.domain.entity.UserId
import org.sopt.and.domain.repository.AuthRepository
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val localPreferences: LocalPreferences,
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository {

    override suspend fun signIn(
        user: User
    ): Result<Token> = runCatching {
        val response = authRemoteDataSource.signIn(
            signInRequest = user.toSignInRequest()
        )
        localPreferences.accessToken = response.result.accessToken
        response.result.toToken()
    }

    override suspend fun signUp(
        user: User
    ): Result<UserId> = runCatching {
        authRemoteDataSource.signUp(
            signUpRequest = user.toSignUpRequest()
        ).result.toUserId()
    }

    override suspend fun signOut(): Result<Unit> = runCatching {
        localPreferences.clearInfo()
    }
}