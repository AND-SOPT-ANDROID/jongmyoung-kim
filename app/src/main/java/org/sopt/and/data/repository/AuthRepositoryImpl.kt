package org.sopt.and.data.repository

import org.sopt.and.data.datasource.local.LocalDataSource
import org.sopt.and.data.datasource.remote.AuthRemoteDataSource
import org.sopt.and.domain.entity.Token
import org.sopt.and.domain.entity.User
import org.sopt.and.domain.entity.UserId
import org.sopt.and.domain.repository.AuthRepository
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository {

    override suspend fun signIn(
        user: User
    ): Result<Token> = runCatching {
        val response = authRemoteDataSource.signIn(
            signInRequest = user.toSignInRequest()
        )
        localDataSource.accessToken = response.result.accessToken
        response.result.toToken()
    }

    override suspend fun signUp(
        user: User
    ): Result<UserId> = runCatching {
        authRemoteDataSource.signUp(
            signUpRequest = user.toSignUpRequest()
        ).result.toId()
    }

    override suspend fun signOut(): Result<Unit> = runCatching {
        localDataSource.clearInfo()
    }

    override suspend fun getUserHobby(): Result<String> = runCatching {
        localDataSource.userHobby
    }
}