package org.sopt.and.presentation.authentication

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import org.sopt.and.R
import org.sopt.and.presentation.theme.Facebook
import org.sopt.and.presentation.theme.Kakao
import org.sopt.and.presentation.theme.Naver
import org.sopt.and.presentation.theme.TWorld
import org.sopt.and.presentation.theme.White

enum class SnsAccountType(
    val type: String,
    @DrawableRes val logo: Int,
    val logoColor: Color
) {
    KAKAO("kakao", R.drawable.logo_kakao, Kakao),
    TWORLD("tworld", R.drawable.logo_tworld, TWorld),
    NAVER("naver", R.drawable.logo_naver, Naver),
    FACEBOOK("facebook", R.drawable.logo_facebook, Facebook),
    APPLE("apple", R.drawable.logo_apple, White)
}
