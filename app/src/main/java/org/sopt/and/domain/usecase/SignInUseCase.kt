package org.sopt.and.domain.usecase

import org.sopt.and.domain.exception.SignInError
import org.sopt.and.domain.repository.UserRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(
        email: String,
        password: String
    ): Result<Unit> {
        return when {
            email.isEmpty() -> Result.failure(SignInError.InvalidEmailException())
            password.isEmpty() -> Result.failure(SignInError.InvalidPasswordException())
            else -> userRepository.signIn(email, password)
        }
    }
}
