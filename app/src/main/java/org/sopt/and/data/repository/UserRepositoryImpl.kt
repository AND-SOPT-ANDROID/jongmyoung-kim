package org.sopt.and.data.repository

import org.sopt.and.data.local.UserDataSource
import org.sopt.and.domain.exception.SignInError
import org.sopt.and.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {

    override fun signIn(
        email: String,
        password: String
    ): Result<Unit> {
        val userInformation = userDataSource.getRemoteUserInfo()
        return if (userInformation.first == email && userInformation.second == password) {
            userDataSource.setUserInfo(email, password)
            Result.success(Unit)
        } else {
            Result.failure(SignInError.SignInFailedException())
        }
    }

    override fun signUp(
        email: String,
        password: String
    ): Result<Unit> {
        return try {
            userDataSource.setRemoteUserInfo(email, password)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun signOut() {
        TODO("Not yet implemented")
    }

}