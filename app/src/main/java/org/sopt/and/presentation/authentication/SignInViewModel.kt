package org.sopt.and.presentation.authentication

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.domain.exception.SignInError
import org.sopt.and.domain.usecase.SignInUseCase
import org.sopt.and.presentation.util.Constants.Companion.MAX_EMAIL
import org.sopt.and.presentation.util.Constants.Companion.MAX_PASSWORD
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
        if (emailInput.length <= MAX_EMAIL) uiState.value = uiState.value.copy(
            emailInput = emailInput
        )
    }

    fun updatePasswordInput(passwordInput: String) {
        if (passwordInput.length <= MAX_PASSWORD) uiState.value = uiState.value.copy(
            passwordInput = passwordInput
        )
    }

    fun updateDialogVisibility(isDialogShown: Boolean) {
        uiState.value = uiState.value.copy(
            isDialogShown = isDialogShown
        )
    }

    fun onSignInClicked() = viewModelScope.launch {
        signInUseCase(uiState.value.emailInput, uiState.value.passwordInput).onSuccess {
            sideEffect.emit(SignInSideEffect.NavigateToHome)
        }.onFailure {
            when (it) {
                is SignInError.InvalidEmailException -> sideEffect.emit(SignInSideEffect.InvalidEmail)
                is SignInError.InvalidPasswordException -> sideEffect.emit(SignInSideEffect.InvalidPassword)
                is SignInError.SignInFailedException -> {
                    sideEffect.emit(SignInSideEffect.SignInFailed)
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

@Immutable
data class SignInUiState(
    val emailInput: String = "",
    val passwordInput: String = "",
    val isDialogShown: Boolean = false
)

sealed interface SignInSideEffect {
    data object InvalidEmail : SignInSideEffect
    data object InvalidPassword : SignInSideEffect
    data object SignInFailed : SignInSideEffect
    data object NavigateToSignUp : SignInSideEffect
    data object NavigateToHome : SignInSideEffect
}
