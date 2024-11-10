package org.sopt.and.data.service

import org.sopt.and.data.service.model.BaseResponse
import org.sopt.and.data.service.model.request.SignInRequest
import org.sopt.and.data.service.model.request.SignUpRequest
import org.sopt.and.data.service.model.response.SignInResponse
import org.sopt.and.data.service.model.response.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthService {
    @POST("/user")
    suspend fun signUp(
        @Body request: SignUpRequest
    ): BaseResponse<SignUpResponse>

    @POST("/login")
    suspend fun signIn(
        @Body request: SignInRequest
    ): BaseResponse<SignInResponse>
}
