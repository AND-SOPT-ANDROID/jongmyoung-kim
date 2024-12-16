package org.sopt.and.presentation.authentication

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.domain.entity.User
import org.sopt.and.domain.exception.SignInError
import org.sopt.and.domain.usecase.SignInUseCase
import org.sopt.and.presentation.authentication.SignInContract.SignInEvent
import org.sopt.and.presentation.authentication.SignInContract.SignInSideEffect
import org.sopt.and.presentation.authentication.SignInContract.SignInUiState
import org.sopt.and.presentation.util.BaseViewModel
import org.sopt.and.presentation.util.Constants.Companion.MAX_LENGTH
import javax.inject.Inject


@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : BaseViewModel<SignInUiState, SignInSideEffect, SignInEvent>() {
    override fun createInitialState(): SignInUiState = SignInUiState()

    override suspend fun handleEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.OnEmailInputChanged -> {
                if (event.emailInput.length <= MAX_LENGTH) setState { copy(emailInput = event.emailInput) }
            }
            is SignInEvent.OnPasswordInputChanged -> {
                if (event.passwordInput.length <= MAX_LENGTH) setState { copy(passwordInput = event.passwordInput) }
            }
            is SignInEvent.OnDialogVisibilityChanged -> setState { copy(isDialogShown = event.isDialogShown) }
            is SignInEvent.OnErrorTextVisibilityChanged -> setState {
                copy(
                    isEmailErrorShown = event.isEmailErrorShown,
                    isPasswordErrorShown = event.isPasswordErrorShown
                )
            }
            is SignInEvent.OnSignInClicked -> onSignInClicked()
            SignInEvent.OnNavigateToSignUp -> onNavigateToSignUp()
        }
    }

    private fun onSignInClicked() = viewModelScope.launch {
        signInUseCase(
            user = with(uiState.value) {
                User(
                    username = emailInput,
                    password = passwordInput,
                    hobby = ""
                )
            }
        ).onSuccess { setSideEffect(SignInSideEffect.NavigateToHome)
        }.onFailure {
            when (it) {
                is SignInError.InvalidEmailException ->
                    setEvent(SignInEvent.OnErrorTextVisibilityChanged(true, false))
                is SignInError.InvalidPasswordException ->
                    setEvent(SignInEvent.OnErrorTextVisibilityChanged(false, true))
                is SignInError.SignInFailedException -> {
                    setEvent(SignInEvent.OnErrorTextVisibilityChanged(false, false))
                    setEvent(SignInEvent.OnDialogVisibilityChanged(true))
                }
            }
        }
    }

    private fun onNavigateToSignUp() = viewModelScope.launch {
        setSideEffect(SignInSideEffect.NavigateToSignUp)
    }
}
