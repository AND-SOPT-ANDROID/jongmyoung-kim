package org.sopt.and.presentation.mypage

import org.sopt.and.presentation.util.UiEvent
import org.sopt.and.presentation.util.UiSideEffect
import org.sopt.and.presentation.util.UiState

class MyPageContract {
    data class MyPageUiState(
        val userHobby: String = "",
        val hobbyInput: String = "",
        val passwordInput: String = "",
        val isBottomSheetVisible: Boolean = false
    ) : UiState

    sealed class MyPageSideEffect : UiSideEffect {
        data object NavigateToSignIn : MyPageSideEffect()
        data class Toast(val message: String) : MyPageSideEffect()
    }

    sealed class MyPageEvent : UiEvent {
        data class OnHobbyInputChanged(val hobbyInput: String) : MyPageEvent()
        data class OnPasswordInputChanged(val passwordInput: String) : MyPageEvent()
        data object OnSignOutClicked : MyPageEvent()
        data object OnModifyButtonClicked : MyPageEvent()
        data class OnSettingClicked(
            val isBottomSheetVisible: Boolean,
            val hobbyInput: String = "",
            val passwordInput: String = ""
        ) : MyPageEvent()
        data class FetchMyHobby(val hobby: String) : MyPageEvent()
    }
}