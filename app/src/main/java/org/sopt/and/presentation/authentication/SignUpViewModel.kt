package org.sopt.and.presentation.authentication

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.domain.entity.User
import org.sopt.and.domain.exception.SignUpError
import org.sopt.and.domain.usecase.SignUpUseCase
import org.sopt.and.presentation.authentication.SignUpContract.SignUpEvent
import org.sopt.and.presentation.authentication.SignUpContract.SignUpSideEffect
import org.sopt.and.presentation.authentication.SignUpContract.SignUpUiState
import org.sopt.and.presentation.util.BaseViewModel
import org.sopt.and.presentation.util.Constants.Companion.MAX_LENGTH
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : BaseViewModel<SignUpUiState, SignUpSideEffect, SignUpEvent>() {
    override fun createInitialState(): SignUpUiState = SignUpUiState()

    override suspend fun handleEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.OnEmailInputChanged -> {
                val isValid = isInputValid(event.emailInput)
                if (event.emailInput.length <= MAX_LENGTH)
                    setState {
                        copy(
                            emailInput = event.emailInput,
                            isEmailValid = isValid,
                            isButtonEnabled = isValid && uiState.value.isPasswordValid && uiState.value.isHobbyValid
                        )
                    }
            }
            is SignUpEvent.OnPasswordInputChanged -> {
                val isValid = isInputValid(event.passwordInput)
                if (event.passwordInput.length <= MAX_LENGTH)
                    setState {
                        copy(
                            passwordInput = event.passwordInput,
                            isPasswordValid = isValid,
                            isButtonEnabled = isValid && uiState.value.isEmailValid && uiState.value.isHobbyValid
                        )
                    }
            }
            is SignUpEvent.OnHobbyInputChanged -> {
                val isValid = isInputValid(event.hobbyInput)
                if (event.hobbyInput.length <= MAX_LENGTH)
                    setState {
                        copy(
                            hobbyInput = event.hobbyInput,
                            isHobbyValid = isValid,
                            isButtonEnabled = isValid && uiState.value.isEmailValid && uiState.value.isPasswordValid
                        )
                    }
            }
            is SignUpEvent.OnDialogVisibilityChanged -> setState { copy(isDialogShown = event.isDialogShown) }
            is SignUpEvent.OnSignUpClicked -> onSignUpClicked()
            is SignUpEvent.OnCancelClicked -> onNavigateToSignIn()
        }
    }

    private fun onSignUpClicked() = viewModelScope.launch {
        signUpUseCase(
            user = with(uiState.value) {
                User(
                    username = emailInput,
                    password = passwordInput,
                    hobby = hobbyInput
                )
            }
        ).onSuccess {
            setSideEffect(SignUpSideEffect.NavigateToSignIn)
        }.onFailure {
            when (it) {
                is SignUpError.DuplicateUserNameException -> {
                    setSideEffect(SignUpSideEffect.Toast(ERROR_MESSAGE))
                }
            }
        }
    }

    private fun onNavigateToSignIn() = viewModelScope.launch {
        setEvent(SignUpEvent.OnDialogVisibilityChanged(false))
        setSideEffect(SignUpSideEffect.NavigateBackToSignIn)
    }

    private fun isInputValid(input: String) = regex.matches(input) && input.isNotBlank()

    companion object {
        private val regex = "^[A-Za-z0-9가-힣]{1,8}$".toRegex()
        private const val ERROR_MESSAGE = "중복된 닉네임입니다." // 임시 에러 문구
    }
}
