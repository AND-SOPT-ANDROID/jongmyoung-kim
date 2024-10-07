package org.sopt.and.presentation.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.presentation.components.CustomTextField
import org.sopt.and.presentation.components.SnsTab
import org.sopt.and.presentation.signup.components.AlertText
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.Background
import org.sopt.and.ui.theme.DarkGray
import org.sopt.and.ui.theme.ExtraDarkGray
import org.sopt.and.ui.theme.LightGray
import org.sopt.and.ui.theme.WavveMain
import org.sopt.and.util.coloredText

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier
) {
    val annotatedString = coloredText(
        string1 = "이메일과 비밀번호",
        stringColor1 = Color.White,
        string2 = "만으로\n",
        stringColor2 = LightGray
    )
    val annotatedString2 = coloredText(
        string1 = "Wavve를 즐길 수 ",
        stringColor1 = Color.White,
        string2 = "있어요!",
        stringColor2 = LightGray
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 76.dp)
                .padding(bottom = 4.dp),
            text = annotatedString + annotatedString2,
            style = MaterialTheme.typography.titleLarge
        )
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomTextField(
                value = "",
                hint = "wavve@example.com",
                onValueChange = {}
            )
            AlertText(
                modifier = Modifier.padding(vertical = 10.dp),
                value = "로그인, 비밀번호 찾기, 알림에 사용되니 정확한 이메일을 입력해 주세요."
            )
            CustomTextField(
                value = "",
                hint = "Wavve 비밀번호 설정",
                onValueChange = {},
                isPassword = true,
                visualTransformation = PasswordVisualTransformation()
            )
            AlertText(
                modifier = Modifier.padding(vertical = 10.dp),
                value = "비밀번호는 8~20자 이내로 영문 대소문자, 숫자, 특수문자 중 3가지 이상 혼용하여 입력해 주세요.",
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(
                    modifier = Modifier.weight(.3f),
                    color = ExtraDarkGray
                )
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = "또는 다른 서비스 계정으로 가입",
                    color = LightGray,
                    style = MaterialTheme.typography.labelMedium
                )
                HorizontalDivider(
                    modifier = Modifier.weight(.3f),
                    color = ExtraDarkGray
                )
            }
            SnsTab(
                modifier = Modifier.padding(top = 24.dp)
            )
            Row(
                modifier = Modifier.padding(top = 40.dp)
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    text = "·",
                    color = DarkGray,
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = "SNS계정으로 간편하게 가입하여 서비스를 이용하실 수 있습니다.\n기존 POOQ 계정 혹은 Wavve 계정과는 연동되지 않으니 이용에 참고하세요.",
                    color = DarkGray,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
            enabled = true,
            colors = ButtonColors(
                containerColor = WavveMain,
                contentColor = Color.White,
                disabledContainerColor = DarkGray,
                disabledContentColor = Color.White
            ),
            onClick = {},
            shape = RectangleShape
        ) {
            Text(
                text = "Wavve 회원가입",
            )
        }
    }
}

@Preview(
    showSystemUi = true
)
@Composable
private fun SignUpScreenPreview() {
    ANDANDROIDTheme {
        SignUpScreen()
    }
}