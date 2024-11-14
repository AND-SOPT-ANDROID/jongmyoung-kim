package org.sopt.and.domain.usecase

import org.sopt.and.domain.entity.UserHobby
import org.sopt.and.domain.repository.MyPageRepository
import javax.inject.Inject

class getHobbyUseCase @Inject constructor(
    private val myPageRepository: MyPageRepository
) {
    suspend operator fun invoke(
        hobby: String
    ): Result<UserHobby> = myPageRepository.getOtherHobby()
}