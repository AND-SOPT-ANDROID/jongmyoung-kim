package org.sopt.and.presentation.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.domain.entity.UserHobby
import org.sopt.and.domain.repository.AuthRepository
import org.sopt.and.domain.repository.MyPageRepository
import org.sopt.and.domain.usecase.ModifyMyInfoUseCase
import org.sopt.and.presentation.mypage.sideeffect.MyPageSideEffect
import org.sopt.and.presentation.mypage.uistate.MyPageUiState
import org.sopt.and.presentation.util.Constants.Companion.MAX_LENGTH
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val myPageRepository: MyPageRepository,
    private val modifyMyInfoUseCase: ModifyMyInfoUseCase
) : ViewModel() {

    val uiState: StateFlow<MyPageUiState>
        field = MutableStateFlow(MyPageUiState())

    val sideEffect: SharedFlow<MyPageSideEffect>
        field = MutableSharedFlow<MyPageSideEffect>()

    init {
        getUserHobby()
    }

    fun updateHobbyInput(hobbyInput: String) {
        if (hobbyInput.length <= MAX_LENGTH) uiState.value = uiState.value.copy(
            hobbyInput = hobbyInput
        )
    }

    fun updatePasswordInput(passwordInput: String) {
        if (passwordInput.length <= MAX_LENGTH) uiState.value = uiState.value.copy(
            passwordInput = passwordInput
        )
    }

    fun updateBottomSheetVisibility(isBottomSheetVisible: Boolean) {
        uiState.value = uiState.value.copy(
            isBottomSheetVisible = isBottomSheetVisible
        )
    }

    fun onSignOutClicked() = viewModelScope.launch {
        authRepository.signOut().onSuccess {
            sideEffect.emit(MyPageSideEffect.NavigateToSignIn)
        }.onFailure {
            sideEffect.emit(MyPageSideEffect.Toast(it.message.orEmpty()))
        }
    }

    fun modifyUserInformation() = viewModelScope.launch {
        modifyMyInfoUseCase(
            userHobby = with(uiState.value) {
                UserHobby(
                    hobby = hobbyInput,
                    password = passwordInput
                )
            }
        ).onSuccess {
            updateBottomSheetVisibility(false)
            if (uiState.value.passwordInput.isEmpty()) getUserHobby() // 취미 변경 시 취미 업데이트
            else onSignOutClicked() // 비밀번호 변경 시 로그아웃
            uiState.value = uiState.value.copy(
                hobbyInput = "",
                passwordInput = ""
            )
            // TODO: regex check
        }.onFailure {
            // TODO: 예외 처리 (네트워크 에러)
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
