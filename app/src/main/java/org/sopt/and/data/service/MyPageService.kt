package org.sopt.and.data.service

import org.sopt.and.data.service.model.BaseResponse
import org.sopt.and.data.service.model.request.HobbyModifyRequest
import org.sopt.and.data.service.model.response.HobbyResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT


interface MyPageService {
    @GET("/user/my-hobby")
    suspend fun getMyHobby(): BaseResponse<HobbyResponse>

    @GET("/user/{no}/hobby")
    suspend fun getOthersHobby(): BaseResponse<HobbyResponse>

    @PUT("/user")
    suspend fun modifyMyHobby(
        @Body hobbyModifyRequest: HobbyModifyRequest
    ): BaseResponse<Any>
}
