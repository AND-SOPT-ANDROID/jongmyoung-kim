package org.sopt.and.domain.usecase

import org.sopt.and.domain.entity.UserHobby
import org.sopt.and.domain.repository.MyPageRepository
import javax.inject.Inject

class GetHobbyUseCase @Inject constructor(
    private val myPageRepository: MyPageRepository
) {
    suspend operator fun invoke(
        no: String
    ): Result<UserHobby> = if (no.isEmpty()) myPageRepository.getMyHobby()
    else myPageRepository.getOtherHobby(no)
}