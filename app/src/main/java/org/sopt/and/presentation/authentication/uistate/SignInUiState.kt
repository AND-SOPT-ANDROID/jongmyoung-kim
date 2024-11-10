package org.sopt.and.presentation.authentication.uistate


data class SignInUiState(
    val emailInput: String = "",
    val passwordInput: String = "",
    val isDialogShown: Boolean = false,
    val isEmailErrorShown: Boolean = false,
    val isPasswordErrorShown: Boolean = false
)
