package org.sopt.and.presentation.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.domain.repository.UserRepository
import org.sopt.and.presentation.mypage.sideeffect.MyPageSideEffect
import org.sopt.and.presentation.mypage.uistate.MyPageUiState
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
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
        userRepository.signOut().onSuccess {
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
