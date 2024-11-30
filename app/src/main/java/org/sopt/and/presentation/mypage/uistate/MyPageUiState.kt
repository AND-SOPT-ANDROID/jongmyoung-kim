package org.sopt.and.presentation.mypage.uistate

data class MyPageUiState(
    val userHobby: String = "",
    val hobbyInput: String = "",
    val passwordInput: String = "",
    val isBottomSheetVisible: Boolean = false
)
