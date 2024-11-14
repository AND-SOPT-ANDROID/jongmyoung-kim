package org.sopt.and.data.datasourceImpl.remote

import org.sopt.and.data.datasource.remote.MyPageRemoteDataSource
import org.sopt.and.data.service.MyPageService
import org.sopt.and.data.service.model.BaseResponse
import org.sopt.and.data.service.model.request.HobbyModifyRequest
import org.sopt.and.data.service.model.response.HobbyResponse
import retrofit2.Response
import javax.inject.Inject


class MyPageRemoteDataSourceImpl @Inject constructor(
    private val myPageService: MyPageService
) : MyPageRemoteDataSource {

    override suspend fun getMyHobby(): BaseResponse<HobbyResponse> = myPageService.getMyHobby()

    override suspend fun getOtherHobby(
        no: String
    ): BaseResponse<HobbyResponse> = myPageService.getOthersHobby(no)

    override suspend fun modifyMyHobby(
        hobbyModifyRequest: HobbyModifyRequest
    ): Response<BaseResponse<String?>?> = myPageService.modifyMyHobby(hobbyModifyRequest)
}
