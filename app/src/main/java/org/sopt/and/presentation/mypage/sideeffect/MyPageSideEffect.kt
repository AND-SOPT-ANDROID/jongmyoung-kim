package org.sopt.and.presentation.mypage.sideeffect

sealed class MyPageSideEffect {
    data object NavigateToSignIn : MyPageSideEffect()
    class Toast(val message: String) : MyPageSideEffect()
}
