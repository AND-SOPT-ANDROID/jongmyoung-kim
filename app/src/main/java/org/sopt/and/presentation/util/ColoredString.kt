package org.sopt.and.presentation.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

/**
 * Colored text
 *
 * 문자열을 서로 다른 색상끼리 조합할 때 사용되는 함수..지만 더 사용할 곳이 없다면 굳이 따로 뺄 필요는 없어 보임
 *
 * @param string1
 * @param stringColor1
 * @param string2
 * @param stringColor2
 * @return
 */
fun coloredText(string1: String, stringColor1: Color, string2: String, stringColor2: Color): AnnotatedString {
    return buildAnnotatedString {
        withStyle(style = SpanStyle(color = stringColor1)) {
            append(string1)
        }
        withStyle(style = SpanStyle(color = stringColor2)) {
            append(string2)
        }
    }
}