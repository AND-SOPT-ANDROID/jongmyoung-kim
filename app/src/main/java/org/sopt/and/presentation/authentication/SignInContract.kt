package org.sopt.and.presentation.authentication

import org.sopt.and.presentation.util.UiEvent
import org.sopt.and.presentation.util.UiSideEffect
import org.sopt.and.presentation.util.UiState

class SignInContract {
    data class SignInUiState(
        val emailInput: String = "",
        val passwordInput: String = "",
        val isDialogShown: Boolean = false,
        val isEmailErrorShown: Boolean = false,
        val isPasswordErrorShown: Boolean = false
    ) : UiState

    sealed class SignInSideEffect : UiSideEffect {
        data object NavigateToSignUp : SignInSideEffect()
        data object NavigateToHome : SignInSideEffect()
    }

    sealed class SignInEvent : UiEvent {
        data class OnEmailInputChanged(val emailInput: String) : SignInEvent()
        data class OnPasswordInputChanged(val passwordInput: String) : SignInEvent()
        data class OnDialogVisibilityChanged(val isDialogShown: Boolean) : SignInEvent()
        data class OnErrorTextVisibilityChanged(
            val isEmailErrorShown: Boolean,
            val isPasswordErrorShown: Boolean
        ) : SignInEvent()
        data object OnSignInClicked : SignInEvent()
        data object OnNavigateToSignUp : SignInEvent()
    }
}
