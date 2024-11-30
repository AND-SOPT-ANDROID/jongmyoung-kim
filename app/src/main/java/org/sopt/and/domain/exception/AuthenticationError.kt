package org.sopt.and.domain.exception


sealed class SignInError : Throwable() {
    class InvalidEmailException : SignInError()
    class InvalidPasswordException : SignInError()
    class SignInFailedException : SignInError()
}

sealed class SignUpError : Throwable() {
    class DuplicateUserNameException() : SignUpError()
}
