package org.sopt.and.presentation.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.domain.entity.User
import org.sopt.and.domain.exception.SignUpError
import org.sopt.and.domain.usecase.SignUpUseCase
import org.sopt.and.presentation.authentication.sideeffect.SignUpSideEffect
import org.sopt.and.presentation.authentication.uistate.SignUpUiState
import org.sopt.and.presentation.util.Constants.Companion.MAX_LENGTH
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
        val isValid = isInputValid(emailInput)
        if (emailInput.length <= MAX_LENGTH) uiState.value = uiState.value.copy(
            emailInput = emailInput,
            isEmailValid = isValid,
            isButtonEnabled = isValid && uiState.value.isPasswordValid && uiState.value.isHobbyValid
        )
    }

    fun updatePasswordInput(passwordInput: String) {
        val isValid = isInputValid(passwordInput)
        if (passwordInput.length <= MAX_LENGTH) uiState.value = uiState.value.copy(
            passwordInput = passwordInput,
            isPasswordValid = isValid,
            isButtonEnabled = isValid && uiState.value.isEmailValid && uiState.value.isHobbyValid
        )
    }

    fun updateHobbyInput(hobbyInput: String) {
        val isValid = isInputValid(hobbyInput)
        if (hobbyInput.length <= MAX_LENGTH) uiState.value = uiState.value.copy(
            hobbyInput = hobbyInput,
            isHobbyValid = isValid,
            isButtonEnabled = isValid && uiState.value.isEmailValid && uiState.value.isPasswordValid
        )
    }

    fun updateDialogVisibility(isDialogShown: Boolean) {
        uiState.value = uiState.value.copy(
            isDialogShown = isDialogShown
        )
    }

    fun onSignUpClicked() = viewModelScope.launch {
        signUpUseCase(
            user = with(uiState.value) {
                User(
                    username = emailInput,
                    password = passwordInput,
                    hobby = hobbyInput
                )
            }
        ).onSuccess {
            sideEffect.emit(SignUpSideEffect.NavigateToSignIn)
        }.onFailure {
            when(it) {
                is SignUpError.DuplicateUserNameException -> {
                    sideEffect.emit(SignUpSideEffect.Toast(ERROR_MESSAGE))
                }
            }
        }
    }

    fun onNavigateToSignIn() = viewModelScope.launch {
        updateDialogVisibility(false)
        sideEffect.emit(SignUpSideEffect.NavigateBackToSignIn)
    }

    private fun isInputValid(input: String) = regex.matches(input) && input.isNotBlank()

    companion object {
        private val regex = "^[A-Za-z0-9가-힣]{1,8}$".toRegex()
        private const val ERROR_MESSAGE = "중복된 닉네임입니다." // 임시 에러 문구
    }
}
