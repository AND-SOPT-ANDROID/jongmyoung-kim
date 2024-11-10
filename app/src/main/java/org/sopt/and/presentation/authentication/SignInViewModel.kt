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
import org.sopt.and.domain.exception.SignInError
import org.sopt.and.domain.usecase.SignInUseCase
import org.sopt.and.presentation.authentication.sideeffect.SignInSideEffect
import org.sopt.and.presentation.authentication.uistate.SignInUiState
import org.sopt.and.presentation.util.Constants.Companion.MAX_LENGTH
import javax.inject.Inject


@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    val uiState: StateFlow<SignInUiState>
        field = MutableStateFlow(SignInUiState())

    val sideEffect: SharedFlow<SignInSideEffect>
        field = MutableSharedFlow<SignInSideEffect>()

    fun updateEmailInput(emailInput: String) {
        if (emailInput.length <= MAX_LENGTH) uiState.value = uiState.value.copy(
            emailInput = emailInput
        )
    }

    fun updatePasswordInput(passwordInput: String) {
        if (passwordInput.length <= MAX_LENGTH) uiState.value = uiState.value.copy(
            passwordInput = passwordInput
        )
    }

    fun updateDialogVisibility(isDialogShown: Boolean) {
        uiState.value = uiState.value.copy(
            isDialogShown = isDialogShown
        )
    }

    fun updateErrorTextVisibility(isEmailErrorShown: Boolean, isPasswordErrorShown: Boolean) {
        uiState.value = uiState.value.copy(
            isEmailErrorShown = isEmailErrorShown,
            isPasswordErrorShown = isPasswordErrorShown
        )
    }

    fun onSignInClicked() = viewModelScope.launch {
        signInUseCase(
            user = with(uiState.value) {
                User(
                    username = emailInput,
                    password = passwordInput,
                    hobby = ""
                )
            }
        ).onSuccess {
            sideEffect.emit(SignInSideEffect.NavigateToHome)
        }.onFailure {
            when (it) {
                is SignInError.InvalidEmailException -> updateErrorTextVisibility(true, false)
                is SignInError.InvalidPasswordException -> updateErrorTextVisibility(false, true)
                is SignInError.SignInFailedException -> {
                    updateErrorTextVisibility(false, false)
                    uiState.value = uiState.value.copy(
                        isDialogShown = true
                    )
                }
            }
        }
    }

    fun onNavigateToSignUp() = viewModelScope.launch {
        sideEffect.emit(SignInSideEffect.NavigateToSignUp)
    }
}
