package org.sopt.and.data.service

import org.sopt.and.data.service.model.BaseResponse
import org.sopt.and.data.service.model.request.HobbyModifyRequest
import org.sopt.and.data.service.model.response.HobbyResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path


interface MyPageService {
    @GET("/user/my-hobby")
    suspend fun getMyHobby(): BaseResponse<HobbyResponse>

    @GET("/user/{no}/hobby")
    suspend fun getOthersHobby(
        @Path("no") no: String
    ): BaseResponse<HobbyResponse>

    @PUT("/user")
    suspend fun modifyMyHobby(
        @Body hobbyModifyRequest: HobbyModifyRequest
    ): Response<BaseResponse<String?>?>
}
