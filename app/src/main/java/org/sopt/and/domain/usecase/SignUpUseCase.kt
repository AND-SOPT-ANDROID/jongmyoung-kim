package org.sopt.and.domain.usecase

import org.sopt.and.domain.exception.SignUpError
import org.sopt.and.domain.repository.UserRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(
        email: String,
        password: String
    ): Result<Unit> {
        val result = userRepository.signUp(email, password)
        result.onFailure {
            if (it is SignUpError.SignUpFailedException) {
                return Result.failure(SignUpError.SignUpFailedException())
            }
        }
        return result
    }
}
