package org.sopt.and.presentation.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.domain.repository.AuthRepository
import org.sopt.and.domain.repository.MyPageRepository
import org.sopt.and.presentation.mypage.sideeffect.MyPageSideEffect
import org.sopt.and.presentation.mypage.uistate.MyPageUiState
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val myPageRepository: MyPageRepository
) : ViewModel() {

    val uiState: StateFlow<MyPageUiState>
        field = MutableStateFlow(MyPageUiState())

    val sideEffect: SharedFlow<MyPageSideEffect>
        field = MutableSharedFlow<MyPageSideEffect>()

    init {
        getUserHobby()
    }

    fun signOut() = viewModelScope.launch {
        authRepository.signOut().onSuccess {
            sideEffect.emit(MyPageSideEffect.NavigateToSignIn)
        }.onFailure {
            sideEffect.emit(MyPageSideEffect.Toast(it.message.orEmpty()))
        }
    }

    fun getUserHobby() = viewModelScope.launch {
        myPageRepository.getMyHobby().onSuccess {
            uiState.value = uiState.value.copy(
                userHobby = it.hobby
            )
        }.onFailure {
            // TODO: 불러오기 실패했을 경우 (네트워크 오류)
        }
    }
}
