package org.sopt.and.presentation.mypage

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.domain.entity.UserHobby
import org.sopt.and.domain.repository.AuthRepository
import org.sopt.and.domain.usecase.GetHobbyUseCase
import org.sopt.and.domain.usecase.ModifyMyInfoUseCase
import org.sopt.and.presentation.mypage.MyPageContract.MyPageEvent
import org.sopt.and.presentation.mypage.MyPageContract.MyPageSideEffect
import org.sopt.and.presentation.mypage.MyPageContract.MyPageUiState
import org.sopt.and.presentation.util.BaseViewModel
import org.sopt.and.presentation.util.Constants.Companion.MAX_LENGTH
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val getHobbyUseCase: GetHobbyUseCase,
    private val modifyMyInfoUseCase: ModifyMyInfoUseCase
) : BaseViewModel<MyPageUiState, MyPageSideEffect, MyPageEvent>() {
    override fun createInitialState(): MyPageUiState = MyPageUiState()
    override suspend fun handleEvent(event: MyPageEvent) {
        when (event) {
            is MyPageEvent.OnHobbyInputChanged -> if (event.hobbyInput.length <= MAX_LENGTH)
                setState { copy(hobbyInput = event.hobbyInput) }
            is MyPageEvent.OnPasswordInputChanged -> if (event.passwordInput.length <= MAX_LENGTH)
                setState { copy(passwordInput = event.passwordInput) }
            is MyPageEvent.OnSignOutClicked -> onSignOutClicked()
            is MyPageEvent.OnModifyButtonClicked -> modifyUserInformation()
            is MyPageEvent.OnSettingClicked -> setState {
                copy(
                    isBottomSheetVisible = event.isBottomSheetVisible,
                    hobbyInput = event.hobbyInput,
                    passwordInput = event.passwordInput
                )
            }
            is MyPageEvent.FetchMyHobby -> setState { copy(userHobby = event.hobby) }
        }
    }

    private fun onSignOutClicked() = viewModelScope.launch {
        authRepository.signOut().onSuccess {
            setSideEffect(MyPageSideEffect.NavigateToSignIn)
        }.onFailure {
            setSideEffect(MyPageSideEffect.Toast(it.message.orEmpty()))
        }
    }

    private fun modifyUserInformation() = viewModelScope.launch {
        modifyMyInfoUseCase(
            userHobby = with(uiState.value) { UserHobby(hobbyInput, passwordInput) }
        ).onSuccess {
            setEvent(MyPageEvent.OnSettingClicked(
                isBottomSheetVisible = false,
                hobbyInput = "",
                passwordInput = ""
            ))
            if (uiState.value.passwordInput.isEmpty()) fetchMyHobby() // 취미 변경 시 취미 업데이트
            else onSignOutClicked() // 비밀번호 변경 시 로그아웃
            // TODO: regex check
        }.onFailure {
            // TODO: 예외 처리 (네트워크 에러)
            setSideEffect(MyPageSideEffect.Toast(it.message.orEmpty()))
        }
    }

    fun fetchMyHobby() = viewModelScope.launch {
        getHobbyUseCase("").onSuccess {
            setEvent(MyPageEvent.FetchMyHobby(hobby = it.hobby))
        }.onFailure {
            setSideEffect(MyPageSideEffect.Toast(it.message.orEmpty()))
        }
    }
}
