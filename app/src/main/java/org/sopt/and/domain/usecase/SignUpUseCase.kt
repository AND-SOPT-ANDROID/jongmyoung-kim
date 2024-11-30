package org.sopt.and.domain.usecase

import org.sopt.and.domain.entity.User
import org.sopt.and.domain.entity.UserId
import org.sopt.and.domain.exception.SignUpError
import org.sopt.and.domain.repository.AuthRepository
import javax.inject.Inject


class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        user: User
    ): Result<UserId> = authRepository.signUp(user).onFailure {
        return Result.failure(SignUpError.DuplicateUserNameException())
    }
}
