package org.sopt.and.presentation.mypage

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.domain.repository.UserRepository
import org.sopt.and.domain.usecase.SignOutUseCase
import org.sopt.and.presentation.authentication.SignUpSideEffect
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase,
    private val userRepository: UserRepository
) : ViewModel() {

    val uiState: StateFlow<MyPageUiState>
        field = MutableStateFlow(MyPageUiState())

    val sideEffect: SharedFlow<MyPageSideEffect>
        field = MutableSharedFlow<MyPageSideEffect>()

    init {
        getUserEmail()
    }

    fun signOut() = viewModelScope.launch {
        signOutUseCase().onSuccess {
            sideEffect.emit(MyPageSideEffect.NavigateToSignIn)
        }.onFailure {
            sideEffect.emit(MyPageSideEffect.Toast(it.message.orEmpty()))
        }
    }

    fun getUserEmail() = viewModelScope.launch {
        uiState.value = uiState.value.copy(
            userEmail = userRepository.getUserEmail().getOrThrow()
        )
    }
}

@Immutable
data class MyPageUiState(
    val userEmail: String = ""
)

sealed class MyPageSideEffect {
    data object NavigateToSignIn : MyPageSideEffect()
    class Toast(val message: String) : MyPageSideEffect()
}
