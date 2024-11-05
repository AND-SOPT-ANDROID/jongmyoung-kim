package org.sopt.and.presentation.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.domain.usecase.SignUpUseCase
import org.sopt.and.presentation.authentication.sideeffect.SignUpSideEffect
import org.sopt.and.presentation.authentication.uistate.SignUpUiState
import org.sopt.and.presentation.util.Constants.Companion.MAX_EMAIL
import org.sopt.and.presentation.util.Constants.Companion.MAX_PASSWORD
import org.sopt.and.presentation.util.Constants.Companion.MIN_EMAIL
import org.sopt.and.presentation.util.Constants.Companion.MIN_PASSWORD
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    val uiState: StateFlow<SignUpUiState>
        field = MutableStateFlow(SignUpUiState())

    val sideEffect: SharedFlow<SignUpSideEffect>
        field = MutableSharedFlow<SignUpSideEffect>()

    fun updateEmailInput(emailInput: String) {
        val isValid = isEmailValid(emailInput)
        if (emailInput.length <= MAX_EMAIL) uiState.value = uiState.value.copy(
            emailInput = emailInput,
            isEmailValid = isValid,
            isButtonEnabled = isValid && uiState.value.isPasswordValid
        )
    }

    fun updatePasswordInput(passwordInput: String) {
        val isValid = isPasswordValid(passwordInput)
        if (passwordInput.length <= MAX_PASSWORD) uiState.value = uiState.value.copy(
            passwordInput = passwordInput,
            isPasswordValid = isValid,
            isButtonEnabled = isValid && uiState.value.isEmailValid
        )
    }

    fun updateDialogVisibility(isDialogShown: Boolean) {
        uiState.value = uiState.value.copy(
            isDialogShown = isDialogShown
        )
    }

    fun onSignUpClicked() = viewModelScope.launch {
        signUpUseCase(uiState.value.emailInput, uiState.value.passwordInput).onSuccess {
            updateDialogVisibility(false)
            sideEffect.emit(SignUpSideEffect.NavigateToSignIn)
        }.onFailure {
            sideEffect.emit(SignUpSideEffect.Toast(it.message.orEmpty()))
        }
    }

    fun onNavigateToSignIn() = viewModelScope.launch {
        updateDialogVisibility(false)
        sideEffect.emit(SignUpSideEffect.NavigateBackToSignIn)
    }

    private fun isEmailValid(email: String) = emailRegex.matches(email) && email.length >= MIN_EMAIL

    private fun isPasswordValid(password: String) =
        passwordRegex.matches(password) && password.length >= MIN_PASSWORD

    companion object Validator {
        private val emailRegex = "[0-9a-zA-Z]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$".toRegex()
        private val passwordRegex =
            ("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])|(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#\$%^&*])|" +
                    "(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%^&*])|(?=.*[a-z])(?=.*[0-9])(?=.*[!@#\$%^&*]).{8,20}$").toRegex()
    }
}
