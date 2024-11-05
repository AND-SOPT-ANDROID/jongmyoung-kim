package org.sopt.and.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// grayscale
val White = Color(0xFFFFFFFF)
val Gray100 = Color(0xFFA5A5A5)
val Gray200 = Color(0xFF717171)
val Gray300 = Color(0xFF2F2F2F)
val Gray400 = Color(0xFF252525)
val Gray500 = Color(0xFF1B1B1B)
val Gray600 = Color(0xFF101010)

// system color
val Error = Color(0xFFFF27A3)

// gradient colors
val GradientBlue = Color(0xFF0075eb)
val GradientTeal = Color(0xFF00B1AB)

// logo Colors
val WavveMain = Color(0xFF0050FF)
val TWorld = Color(0xFF3617CE)
val Naver = Color(0xFF1ec800)
val Kakao = Color(0xFFFEE500)
val Facebook = Color(0xFF4267B3)

@Immutable
data class AndAndroidColors(
    // grayscale
    val white: Color,
    val gray100: Color,
    val gray200: Color,
    val gray300: Color,
    val gray400: Color,
    val gray500: Color,
    val gray600: Color,

    // system color
    val error: Color,

    // gradient colors
    val gradientBlue: Color,
    val gradientTeal: Color,

    // logo colors
    val wavveMain: Color,
    val tWorld: Color,
    val naver: Color,
    val kakao: Color,
    val facebook: Color
)

val defaultAndAndroidColors = AndAndroidColors(
    // grayscale
    white = White,
    gray100 = Gray100,
    gray200 = Gray200,
    gray300 = Gray300,
    gray400 = Gray400,
    gray500 = Gray500,
    gray600 = Gray600,

    // system color
    error = Error,

    // gradient colors
    gradientBlue = GradientBlue,
    gradientTeal = GradientTeal,

    // logo colors
    wavveMain = WavveMain,
    tWorld = TWorld,
    naver = Naver,
    kakao = Kakao,
    facebook = Facebook
)

val LocalAndAndroidColors = staticCompositionLocalOf { defaultAndAndroidColors }