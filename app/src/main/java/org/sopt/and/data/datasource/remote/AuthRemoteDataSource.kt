package org.sopt.and.data.datasource.remote

import org.sopt.and.data.service.model.BaseResponse
import org.sopt.and.data.service.model.request.SignInRequest
import org.sopt.and.data.service.model.request.SignUpRequest
import org.sopt.and.data.service.model.response.SignInResponse
import org.sopt.and.data.service.model.response.SignUpResponse


interface AuthRemoteDataSource {
    suspend fun signIn(signInRequest: SignInRequest): BaseResponse<SignInResponse>

    suspend fun signUp(signUpRequest: SignUpRequest): BaseResponse<SignUpResponse>
}
