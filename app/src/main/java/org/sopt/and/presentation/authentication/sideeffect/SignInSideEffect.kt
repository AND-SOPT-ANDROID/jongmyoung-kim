package org.sopt.and.presentation.authentication.sideeffect


sealed interface SignInSideEffect {
    data object NavigateToSignUp : SignInSideEffect
    data object NavigateToHome : SignInSideEffect
}
