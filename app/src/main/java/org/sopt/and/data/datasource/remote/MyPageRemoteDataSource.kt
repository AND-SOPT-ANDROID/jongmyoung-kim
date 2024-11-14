package org.sopt.and.data.datasource.remote

import org.sopt.and.data.service.model.BaseResponse
import org.sopt.and.data.service.model.request.HobbyModifyRequest
import org.sopt.and.data.service.model.response.HobbyResponse
import retrofit2.Response


interface MyPageRemoteDataSource {

    suspend fun getMyHobby(): BaseResponse<HobbyResponse>

    suspend fun getOtherHobby(): BaseResponse<HobbyResponse>

    suspend fun modifyMyHobby(
        hobbyModifyRequest: HobbyModifyRequest
    ): Response<BaseResponse<String?>?>
}
