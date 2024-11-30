package org.sopt.and.domain.usecase

import org.sopt.and.domain.entity.Token
import org.sopt.and.domain.entity.User
import org.sopt.and.domain.exception.SignInError
import org.sopt.and.domain.repository.AuthRepository
import javax.inject.Inject


class SignInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        user: User
    ): Result<Token> = when {
        user.username.isEmpty() -> Result.failure(SignInError.InvalidEmailException())
        user.password.isEmpty() -> Result.failure(SignInError.InvalidPasswordException())
        else -> authRepository.signIn(user).onFailure {
            return Result.failure(SignInError.SignInFailedException())
        }
    }
}
