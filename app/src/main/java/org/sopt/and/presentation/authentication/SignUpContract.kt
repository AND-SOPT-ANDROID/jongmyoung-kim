package org.sopt.and.presentation.authentication

import org.sopt.and.presentation.util.UiEvent
import org.sopt.and.presentation.util.UiSideEffect
import org.sopt.and.presentation.util.UiState

class SignUpContract {
    data class SignUpUiState(
        val emailInput: String = "",
        val passwordInput: String = "",
        val hobbyInput: String = "",
        val isEmailValid: Boolean = false,
        val isPasswordValid: Boolean = false,
        val isHobbyValid: Boolean = false,
        val isDialogShown: Boolean = false,
        val isButtonEnabled: Boolean = false
    ) : UiState

    sealed class SignUpSideEffect : UiSideEffect {
        data object NavigateToSignIn : SignUpSideEffect()
        data object NavigateBackToSignIn : SignUpSideEffect()
        data class Toast(val message: String) : SignUpSideEffect()
    }

    sealed class SignUpEvent : UiEvent {
        data class OnEmailInputChanged(val emailInput: String) : SignUpEvent()
        data class OnPasswordInputChanged(val passwordInput: String) : SignUpEvent()
        data class OnHobbyInputChanged(val hobbyInput: String) : SignUpEvent()
        data class OnDialogVisibilityChanged(val isDialogShown: Boolean) : SignUpEvent()
        data object OnSignUpClicked : SignUpEvent()
        data object OnCancelClicked : SignUpEvent()
    }
}
