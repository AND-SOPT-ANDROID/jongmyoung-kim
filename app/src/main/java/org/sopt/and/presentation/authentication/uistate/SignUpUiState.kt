package org.sopt.and.presentation.authentication.uistate

data class SignUpUiState(
    val emailInput: String = "",
    val passwordInput: String = "",
    val isEmailValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isDialogShown: Boolean = false,
    val isButtonEnabled: Boolean = false
)
