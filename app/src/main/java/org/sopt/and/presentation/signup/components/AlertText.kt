package org.sopt.and.presentation.signup.components

import android.text.style.BackgroundColorSpan
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.ErrorText
import org.sopt.and.ui.theme.LightGray

@Composable
fun AlertText(
    modifier: Modifier = Modifier,
    value: String,
    isPasswordValid: Boolean = true
) {
    val textColor: Color = if(isPasswordValid) LightGray else ErrorText

    Row(
        modifier = modifier
    ) {
        Icon(
            modifier = Modifier.padding(end = 4.dp),
            painter = painterResource(R.drawable.ic_alert),
            contentDescription = null,
            tint = textColor
        )
        Text(
            text = value,
            color = textColor,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun AlertTextPreview() {
    ANDANDROIDTheme {
        AlertText(
            value = "로그인, 비밀번호 찾기, 알림에 사용되 정확한 이메일을 입력해 주세요.",
            isPasswordValid = false
        )
    }
}