package org.sopt.and.domain.usecase

import org.sopt.and.domain.entity.UserHobby
import org.sopt.and.domain.repository.MyPageRepository
import javax.inject.Inject


class ModifyMyInfoUseCase @Inject constructor(
    private val myPageRepository: MyPageRepository
) {
    suspend operator fun invoke(
        userHobby: UserHobby
    ): Result<Unit> = myPageRepository.modifyMyHobby(
        hobbyModifyRequest = userHobby.toHobbyModifyRequest()
    )
}