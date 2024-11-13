package org.sopt.and.data.repository

import org.sopt.and.data.datasource.remote.MyPageRemoteDataSource
import org.sopt.and.data.service.model.request.HobbyModifyRequest
import org.sopt.and.domain.entity.UserHobby
import org.sopt.and.domain.repository.MyPageRepository
import javax.inject.Inject


class MyPageRepositoryImpl @Inject constructor(
    private val myPageRemoteDataSource: MyPageRemoteDataSource
) : MyPageRepository {
    override suspend fun getMyHobby(): Result<UserHobby> = runCatching {
        myPageRemoteDataSource.getMyHobby().result.toUserHobby()
    }
    override suspend fun getOtherHobby(): Result<UserHobby> = runCatching {
        myPageRemoteDataSource.getOtherHobby().result.toUserHobby()
    }
    override suspend fun modifyMyHobby(
        hobbyModifyRequest: HobbyModifyRequest
    ): Result<Unit> = runCatching {
        myPageRemoteDataSource.modifyMyHobby(hobbyModifyRequest)
    }
}
