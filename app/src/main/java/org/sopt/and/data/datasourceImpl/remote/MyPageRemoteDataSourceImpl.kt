package org.sopt.and.data.datasourceImpl.remote

import org.sopt.and.data.datasource.remote.MyPageRemoteDataSource
import org.sopt.and.data.service.MyPageService
import org.sopt.and.data.service.model.BaseResponse
import org.sopt.and.data.service.model.request.HobbyModifyRequest
import org.sopt.and.data.service.model.response.HobbyResponse
import javax.inject.Inject


class MyPageRemoteDataSourceImpl @Inject constructor(
    private val myPageService: MyPageService
) : MyPageRemoteDataSource {

    override suspend fun getMyHobby(): BaseResponse<HobbyResponse> = myPageService.getMyHobby()

    override suspend fun getOtherHobby(): BaseResponse<HobbyResponse> = myPageService.getOthersHobby()

    override suspend fun modifyMyHobby(
        hobbyModifyRequest: HobbyModifyRequest
    ): BaseResponse<Any> = myPageService.modifyMyHobby(hobbyModifyRequest)
}
