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
        return if (email.isEmpty()) {
            Result.failure(SignInError.InvalidEmailException())
        } else if (password.isEmpty()) {
            Result.failure(SignInError.InvalidPasswordException())
        } else {
            val result = userRepository.signIn(email, password)

            result.onFailure {
                if (it is SignInError.SignInFailedException) {
                    return Result.failure(SignInError.SignInFailedException())
                }
            }
            return result
        }
    }
}
