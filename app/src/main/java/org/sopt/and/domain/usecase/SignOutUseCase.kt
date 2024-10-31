package org.sopt.and.domain.usecase

import org.sopt.and.domain.repository.UserRepository
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(): Result<Unit> {
        return userRepository.signOut()
    }
}