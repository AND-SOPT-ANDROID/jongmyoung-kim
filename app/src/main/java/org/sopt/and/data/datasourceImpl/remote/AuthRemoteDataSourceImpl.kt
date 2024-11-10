package org.sopt.and.data.datasourceImpl.remote

import org.sopt.and.data.datasource.remote.AuthRemoteDataSource
import org.sopt.and.data.service.AuthService
import org.sopt.and.data.service.model.BaseResponse
import org.sopt.and.data.service.model.request.SignInRequest
import org.sopt.and.data.service.model.request.SignUpRequest
import org.sopt.and.data.service.model.response.SignInResponse
import org.sopt.and.data.service.model.response.SignUpResponse
import javax.inject.Inject


class AuthRemoteDataSourceImpl @Inject constructor(
    private val authService: AuthService
) : AuthRemoteDataSource {

    override suspend fun signIn(
        signInRequest: SignInRequest
    ): BaseResponse<SignInResponse> = authService.signIn(signInRequest)

    override suspend fun signUp(
        signUpRequest: SignUpRequest
    ): BaseResponse<SignUpResponse> = authService.signUp(signUpRequest)
}
