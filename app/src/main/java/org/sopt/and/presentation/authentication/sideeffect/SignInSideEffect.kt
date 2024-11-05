package org.sopt.and.presentation.authentication.sideeffect

sealed interface SignInSideEffect {
    data object InvalidEmail : SignInSideEffect
    data object InvalidPassword : SignInSideEffect
    data object SignInFailed : SignInSideEffect
    data object NavigateToSignUp : SignInSideEffect
    data object NavigateToHome : SignInSideEffect
}
