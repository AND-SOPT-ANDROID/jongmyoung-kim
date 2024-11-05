package org.sopt.and.presentation.authentication.sideeffect

sealed class SignUpSideEffect {
    data object NavigateToSignIn : SignUpSideEffect()
    data object NavigateBackToSignIn : SignUpSideEffect()
    data class Toast(val message: String) : SignUpSideEffect()
}
